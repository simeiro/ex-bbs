package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * commentsテーブルを操作するリポジトリ.
 */
@Repository
@Transactional
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

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
}
