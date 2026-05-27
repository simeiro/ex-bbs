package com.example.ex_bbs.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 記事のフォーム.
 */
@Getter
@Setter
@ToString
public class ArticleForm {
    /**
     * 名前.
     */
    private String name;
    /**
     * 投稿内容
     */
    private String content;
}
