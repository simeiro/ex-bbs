package com.example.ex_bbs.controller;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.form.ArticleForm;
import com.example.ex_bbs.repository.ArticleRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 記事画面を操作するコントローラー.
 */
@Controller
public class ArticleController {

    @Autowired
    private ServletContext application;
    @Autowired
    private ArticleRepository articleRepository;

    /**
     *
     * @return
     */
    @GetMapping("")
    public String index(ArticleForm articleForm) {
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
        application.setAttribute("articles", articleRepository.findAll());

        return "article";
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
