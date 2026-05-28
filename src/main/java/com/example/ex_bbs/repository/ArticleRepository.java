package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリ.
 */
@Repository
@Transactional
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

    /**
     * 記事情報一覧を取得する.
     *
     * @return 記事情報一覧
     */
    public List<Article> findAll() {
        //language=sql
        String sql = """
                SELECT
                    id,
                    name,
                    content
                FROM
                    articles
                ORDER BY 
                    id DESC 
                """;

        return template.query(sql, ARTICLE_ROW_MAPPER);
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
     * 記事を削除する.
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
