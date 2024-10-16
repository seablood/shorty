package com.cyj.shorty.repository;


import com.cyj.shorty.domain.ShortenUrl;

public interface ShortenListRepository {
    ShortenUrl save(ShortenUrl shortenUrl);
    ShortenUrl findByShortUrl(String shortUrl);
    boolean existsShortenUrlByShortUrl(String shortUrl);
}
