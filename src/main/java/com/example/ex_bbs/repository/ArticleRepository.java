package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * articlesテーブルを操作するリポジトリ.
 */
@Repository
@Transactional
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final ResultSetExtractor<List<Article>> ARTICLE_WITH_COMMENTS_EXTRACTOR = rs -> {
        Map<Long, Article> map = new LinkedHashMap<>();

        while (rs.next()) {
            Long articleId = rs.getLong("a_id");
            Article article = map.get(articleId);

            //新たな記事があれば追加
            if (article == null) {
                article = Article.builder()
                        .id(articleId)
                        .name(rs.getString("a_name"))
                        .content(rs.getString("a_content"))
                        .commentList(new ArrayList<>())
                        .build();

                map.put(articleId, article);
            }

            //コメントがあれば記事に追加
            Long commentId = rs.getLong("c_id");
            //rs.getLongの戻り値はプリミティブ型なのでnullの場合0に変換される
            if (commentId != 0) {
                Comment comment = Comment.builder()
                        .id(commentId)
                        .name(rs.getString("c_name"))
                        .content(rs.getString("c_content"))
                        .articleId(rs.getLong("c_article_id"))
                        .build();

                article.getCommentList().add(comment);
            }
        }
        return new ArrayList<>(map.values());
    };

    /**
     * 記事情報と記事に含まれるコメントの一覧を取得する.
     *
     * @return 記事情報一覧
     */
    public List<Article> findAll() {
        //language=sql
        String sql = """
                SELECT
                    a.id           AS a_id,
                    a.name         AS a_name,
                    a.content      AS a_content,
                    c.id           AS c_id,
                    c.name         AS c_name,
                    c.content      AS c_content,
                    c.article_id   AS c_article_id
                FROM
                    articles AS a
                LEFT OUTER JOIN 
                    comments AS c
                ON 
                    a.id = c.article_id
                ORDER BY 
                    a.id DESC,
                    c.id DESC
                """;

        return template.query(sql, ARTICLE_WITH_COMMENTS_EXTRACTOR);
    }

    /**
     * 記事を追加する.
     *
     * @param article 追加する記事
     */
    public void insert(Article article) {
        //language=sql
        String sql = """
                INSERT INTO articles(name, content)
                VALUES 
                    (:name, :content)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);

        template.update(sql, param);

    }

    /**
     * 記事と記事に含まれるコメントを削除する.
     *
     * @param id 削除する記事ID
     */
    public void deleteById(Long id) {
        //language=sql
        String sql = """
                DELETE FROM articles
                WHERE 
                    id = :id
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        template.update(sql, param);
    }
}
