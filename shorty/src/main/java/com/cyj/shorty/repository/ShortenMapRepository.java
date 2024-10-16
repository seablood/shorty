package com.cyj.shorty.repository;

import com.cyj.shorty.domain.ShortenUrl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("prod")
public class ShortenMapRepository implements ShortenListRepository{
    private Map<String, ShortenUrl> repository = new ConcurrentHashMap<>();

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        repository.put(shortenUrl.getShortUrl(), shortenUrl);
        return shortenUrl;
    }

    @Override
    public ShortenUrl findByShortUrl(String shortUrl) {
        return repository.get(shortUrl);
    }

    @Override
    public boolean existsShortenUrlByShortUrl(String shortUrl) {
        if(findByShortUrl(shortUrl) != null) return true;
        else return false;
    }
}
