package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * commentsテーブルを操作するリポジトリ.
 */
@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

    /**
     * 対応する記事IDのコメント一覧を取得する.
     *
     * @param articleId 記事ID
     * @return　対応する記事IDのコメント一覧
     */
    public List<Comment> findByArticleId(Long articleId) {
        //language=sql
        String sql = """
                SELECT
                    id,
                    name,
                    content,
                    article_id
                FROM
                    comments
                WHERE
                    article_id = :article_id
                ORDER BY
                    id
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("article_id", articleId);

        return template.query(sql, param, COMMENT_ROW_MAPPER);
    }

    /**
     * コメントを追加する.
     *
     * @param comment 追加するコメント
     */
    public void insert(Comment comment) {
        //language=sql
        String sql = """
                INSERT INTO comments(name, content, article_id)
                VALUES 
                    (:name, :content, :articleId)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);

        template.update(sql, param);
    }

    /**
     * 対応する記事のコメントを全て削除する.
     *
     * @param articleId 記事ID
     */
    public void deleteByArticleId(Long articleId) {
        //language=sql
        String sql = """
                DELETE FROM comments
                WHERE
                    article_id = :articleId
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("articleId", articleId);

        template.update(sql, param);
    }
}
