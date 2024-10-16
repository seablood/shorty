package com.cyj.shorty.dto;

import com.cyj.shorty.domain.ShortenUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortenUrlDto {
    private String originalUrl;

    private String shortUrl;

    private Integer redirectCount;

    public static ShortenUrlDto toDto(ShortenUrl shortenUrl){
        return new ShortenUrlDto(shortenUrl.getOriginalUrl(), shortenUrl.getShortUrl(), shortenUrl.getRedirectCount());
    }
}
