package com.example.ex_bbs.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * commentsテーブルのドメイン.
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "article_id")
    private Long articleId;
}
