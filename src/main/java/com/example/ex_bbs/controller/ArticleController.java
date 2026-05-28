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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 記事、コメント情報一覧を所得し、掲示板画面を表示する.
     *
     * @param articleForm 記事のフォーム
     * @param commentForm コメントのフォーム
     * @return 掲示板画面
     */
    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        //掲示板情報を全て取得し、表示させる
        List<Article> articles = articleRepository.findAll();

        model.addAttribute("articles", articles);

        return "article";
    }

    /**
     * 記事情報を追加し、掲示板画面へリダイレクトする.
     *
     * @param articleForm 記事のフォーム
     * @return　掲示板画面へリダイレクト
     */
    @PostMapping("insert-article")
    public String insertArticle(ArticleForm articleForm) {
        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        articleRepository.insert(article);

        return "redirect:/";
    }

    /**
     * コメント情報を追加し、掲示板画面へリダイレクトする.
     *
     * @param commentForm コメントフォーム
     * @return 掲示板画面へリダイレクト
     */
    @PostMapping("insert-comment")
    public String insertComment(CommentForm commentForm) {
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
        commentRepository.deleteByArticleId(id);
        articleRepository.deleteById(id);

        return "redirect:/";
    }
}
