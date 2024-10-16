package com.cyj.shorty.application;

import com.cyj.shorty.dto.CreateShortenUrlDto;
import com.cyj.shorty.dto.ShortenUrlDto;
import com.cyj.shorty.service.ShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ShortenUrlServiceTest {
    @Autowired
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("URL 단축 후 단축된 URL을 조회")
    public void saveAndGetInfo(){
        String originalUrl = "https://www.naver.com";
        CreateShortenUrlDto createShortenUrlDto = new CreateShortenUrlDto(originalUrl);
        ShortenUrlDto shortenUrlDto = shortenUrlService.save(createShortenUrlDto);
        String shortUrl = shortenUrlDto.getShortUrl();

        ShortenUrlDto getInfo = shortenUrlService.getShortenUrlInfo(shortUrl);

        assertTrue(getInfo.getOriginalUrl().equals(shortenUrlDto.getOriginalUrl()));
        assertTrue(getInfo.getShortUrl().equals(shortenUrlDto.getShortUrl()));
    }

}