package com.example.ex_bbs.form;

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
     * 対応する記事のID
     */
    private Long id;
    /**
     * 名前.
     */
    private String name;
    /**
     * コメント内容.
     */
    private String comment;
}
