package com.example.ex_bbs.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * articlesテーブルのドメイン.
 */
@Entity
@Table(name = "articles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
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
     * 投稿内容.
     */
    private String content;
    /**
     * コメント一覧.
     */
    @OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Comment> commentList;
}
