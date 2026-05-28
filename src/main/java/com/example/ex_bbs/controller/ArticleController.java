package com.example.ex_bbs.controller;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.domain.Comment;
import com.example.ex_bbs.form.ArticleForm;
import com.example.ex_bbs.form.CommentForm;
import com.example.ex_bbs.repository.ArticleRepository;
import com.example.ex_bbs.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 記事画面を操作するコントローラー.
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 掲示板画面の表示に必要な記事情報一覧をModelへ追加する.
     *
     * @return 記事情報一覧
     */
    @ModelAttribute("articles")
    public List<Article> setUpArticles() {
        return articleRepository.findAll();
    }

    /**
     * 空の記事投稿フォームをModelへ追加する.
     *
     * @return 空の記事投稿フォーム
     */
    @ModelAttribute
    public ArticleForm setUpArticleForm() {
        return new ArticleForm();
    }

    /**
     * 空のコメント投稿フォームをModelへ追加する.
     *
     * @return 空のコメント投稿フォーム
     */
    @ModelAttribute
    public CommentForm setUpCommentForm() {
        return new CommentForm();
    }

    /**
     * 記事、コメント情報一覧を所得し、掲示板画面を表示する.
     *
     * @return 掲示板画面
     */
    @GetMapping("")
    public String index() {
        return "article";
    }

    /**
     * 記事情報を追加し、掲示板画面へリダイレクトする.
     *
     * @param articleForm 記事投稿フォーム
     * @param result      エラーを確認するためのresult
     * @return 掲示板画面
     */
    @PostMapping("insert-article")
    public String insertArticle(@Validated ArticleForm articleForm, BindingResult result) {
        if (result.hasErrors()) {
            return "article";
        }

        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        articleRepository.insert(article);

        return "redirect:/";
    }

    /**
     * コメント情報を追加し、掲示板画面へリダイレクトする.
     *
     * @param commentForm コメント投稿フォーム
     * @param result      エラーを確認するためのresult
     * @return 掲示板画面
     */
    @PostMapping("insert-comment")
    public String insertComment(@Validated CommentForm commentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "article";
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForm, comment);
        commentRepository.insert(comment);

        return "redirect:/";
    }

    /**
     * 記事とその記事にあるコメントを削除し、掲示板画面へリダイレクトする.
     *
     * @param id 記事ID
     * @return　掲示板画面へリダイレクト
     */
    @PostMapping("delete-article")
    public String deleteArticle(Long id) {
        articleRepository.deleteById(id);

        return "redirect:/";
    }
}
