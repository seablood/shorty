package com.cyj.shorty.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shortenUrl")
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;

    private String shortUrl;

    private Integer redirectCount;

    public void createShortUrl(){
        String base64 = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        StringBuilder shortUrl = new StringBuilder();

        for (int i = 0; i < 8; i++){
            int idx = (int)(Math.random() * base64.length());
            char shortUrlChar = base64.charAt(idx);
            shortUrl.append(shortUrlChar);
        }

        this.shortUrl = shortUrl.toString();
    }

    public void addRedirectCount(){
        this.redirectCount++;
    }

    @Builder
    public ShortenUrl(String originalUrl){
        this.originalUrl = originalUrl;
        this.redirectCount = 0;
    }
}
