package com.example.ex_bbs.controller;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.form.ArticleForm;
import com.example.ex_bbs.form.CommentForm;
import com.example.ex_bbs.repository.ArticleRepository;
import com.example.ex_bbs.repository.CommentRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 記事画面を操作するコントローラー.
 */
@Controller
public class ArticleController {

    @Autowired
    private ServletContext application;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     *
     * @return
     */
    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm) {
        //掲示板情報を全て取得し、表示させる
        List<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            article.setCommentList(commentRepository.findByArticleId(article.getId()));
        }
        application.setAttribute("articles", articles);

        return "article";
    }

    /**
     *
     * @return
     */
    @PostMapping("insert-article")
    public String insertArticle(ArticleForm articleForm) {
        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        articleRepository.insert(article);

        //CommentForm　はこのメソッドではModelAttributeされない
        //そのためためリダイレクトを行い、ModelAttributeさせる
        return "redirect:/";
    }

    /**
     *
     * @return
     */
    @PostMapping("insert-comment")
    public String insertComment() {
        return "article";
    }

    /**
     *
     * @return
     */
    @PostMapping("delete-article")
    public String deleteArticle() {
        return "article";
    }
}
