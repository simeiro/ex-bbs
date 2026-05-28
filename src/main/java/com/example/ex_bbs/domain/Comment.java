package com.example.ex_bbs.domain;

import lombok.*;

/**
 * commentsテーブルのドメイン.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * ID.
     */
    private Long id;
    /**
     * 名前.
     */
    private String name;
    /**
     * コメント内容.
     */
    private String content;
    /**
     * 対応する記事のID.
     */
    private Long articleId;
}
