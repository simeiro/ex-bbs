package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * commentsテーブルを操作するリポジトリ.
 */
@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
