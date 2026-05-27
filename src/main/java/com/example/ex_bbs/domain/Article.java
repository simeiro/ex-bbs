package com.example.ex_bbs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * articlesテーブルのドメイン.
 */
@Getter
@Setter
@ToString
public class Article {
    /**
     * ID.
     */
    private Long id;
    /**
     * 名前.
     */
    private String name;
    /**
     * 投稿内容.
     */
    private String content;
    /**
     * コメント一覧.
     */
    private List<Comment> commentList;
}
