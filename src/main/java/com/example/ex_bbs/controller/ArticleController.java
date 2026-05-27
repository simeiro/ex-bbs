package com.example.ex_bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 記事画面を操作するコントローラー.
 */
@Controller
public class ArticleController {

    /**
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return "article";
    }

    /**
     *
     * @return
     */
    @PostMapping("insert-article")
    public String insertArticle() {
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
