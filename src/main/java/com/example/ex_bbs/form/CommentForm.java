package com.example.ex_bbs.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * コメントのフォーム.
 */
@Getter
@Setter
@ToString
public class CommentForm {
    /**
     * 対応する記事のID.
     */
    private Long articleId;
    /**
     * 名前.
     */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50字以内で入力してください")
    private String name;
    /**
     * コメント内容.
     */
    @NotBlank(message = "コメントを入力してください")
    private String content;
}
