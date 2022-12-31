package com.shoppingmall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private int rating;

    @Builder
    public Review(String title, String content, int rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
