package com.cyj.shorty.service;

import com.cyj.shorty.domain.ShortenUrl;
import com.cyj.shorty.dto.CreateShortenUrlDto;
import com.cyj.shorty.dto.ShortenUrlDto;
import com.cyj.shorty.repository.ShortenUrlRepository;
import com.cyj.shorty.util.DuplicationShortUrlException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;

    @Transactional
    public ShortenUrlDto save(CreateShortenUrlDto createShortenUrlDto){
        ShortenUrl shortenUrl = CreateShortenUrlDto.toEntity(createShortenUrlDto);

        existShortUrl(shortenUrl);

        ShortenUrlDto shortenUrlDto = ShortenUrlDto.toDto(shortenUrlRepository.save(shortenUrl));

        return shortenUrlDto;
    }

    @Transactional
    public String getOriginalUrl(String shortUrl){
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL을 찾을 수 없습니다."));
        shortenUrl.addRedirectCount();

        return shortenUrl.getOriginalUrl();
    }

    public ShortenUrlDto getShortenUrlInfo(String shortUrl){
        ShortenUrl shortenUrl = shortenUrlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL을 찾을 수 없습니다."));

        return ShortenUrlDto.toDto(shortenUrl);
    }

    public void existShortUrl(ShortenUrl shortenUrl){
        final int MAX_REPEAT = 5;
        int count = 0;

        while (MAX_REPEAT > count){
            shortenUrl.createShortUrl();

            if(!shortenUrlRepository.existsShortenUrlByShortUrl(shortenUrl.getShortUrl())){
                break;
            }
            count++;
        }

        if(MAX_REPEAT == count) throw new DuplicationShortUrlException("URL이 중복되어 생성이 불가능합니다.");
    }
}
