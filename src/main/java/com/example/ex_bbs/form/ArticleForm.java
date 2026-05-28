package com.example.ex_bbs.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "投稿者名を入力してください")
    @Size(max = 50, message = "投稿者名は50字以内で入力してください")
    private String name;
    /**
     * 投稿内容.
     */
    @NotBlank(message = "投稿内容を入力してください")
    private String content;
}
