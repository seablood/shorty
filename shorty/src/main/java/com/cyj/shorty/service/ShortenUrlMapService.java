/*package com.cyj.shorty.service;

import com.cyj.shorty.domain.ShortenUrl;
import com.cyj.shorty.dto.CreateShortenUrlDto;
import com.cyj.shorty.dto.ShortenUrlDto;
import com.cyj.shorty.repository.ShortenListRepository;
import com.cyj.shorty.util.DuplicationShortUrlException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenUrlMapService {
    private final ShortenListRepository shortenListRepository;

    public ShortenUrlDto save(CreateShortenUrlDto createShortenUrlDto){
        ShortenUrl shortenUrl = CreateShortenUrlDto.toEntity(createShortenUrlDto);

        existShortUrl(shortenUrl);

        ShortenUrlDto shortenUrlDto = ShortenUrlDto.toDto(shortenListRepository.save(shortenUrl));

        return shortenUrlDto;
    }

    public String getOriginalUrl(String shortUrl){
        ShortenUrl shortenUrl = shortenListRepository.findByShortUrl(shortUrl);
        if(shortenUrl == null) throw new IllegalArgumentException("URL을 찾을 수 없습니다.");
        shortenUrl.addRedirectCount();

        return shortenUrl.getOriginalUrl();
    }

    public ShortenUrlDto getShortenUrlInfo(String shortUrl){
        ShortenUrl shortenUrl = shortenListRepository.findByShortUrl(shortUrl);
        if(shortenUrl == null) throw new IllegalArgumentException("URL을 찾을 수 없습니다.");

        return ShortenUrlDto.toDto(shortenUrl);
    }

    public void existShortUrl(ShortenUrl shortenUrl){
        final int MAX_REPEAT = 5;
        int count = 0;

        while (MAX_REPEAT > count){
            shortenUrl.createShortUrl();

            if(!shortenListRepository.existsShortenUrlByShortUrl(shortenUrl.getShortUrl())){
                break;
            }
            count++;
        }

        if(MAX_REPEAT == count) throw new DuplicationShortUrlException("URL이 중복되어 생성이 불가능합니다.");
    }

}*/
