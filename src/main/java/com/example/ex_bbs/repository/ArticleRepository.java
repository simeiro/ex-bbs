package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリ.
 */
@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.commentList ORDER BY a.id DESC")
    List<Article> findAllByOrderByDesc();

}
