package com.cyj.shorty.repository;

import com.cyj.shorty.domain.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {
    Optional<ShortenUrl> findByShortUrl(String shortUrl);

    boolean existsShortenUrlByShortUrl(String shortUrl);
}
