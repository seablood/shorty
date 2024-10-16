package com.cyj.shorty.application;

import com.cyj.shorty.dto.CreateShortenUrlDto;
import com.cyj.shorty.repository.ShortenUrlRepository;
//import com.cyj.shorty.service.ShortenUrlMapService;
import com.cyj.shorty.service.ShortenUrlService;
import com.cyj.shorty.util.DuplicationShortUrlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortenUrlServiceUnitTest {
    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("URL 중복 생성 시 예외 반환")
    public void DuplicationExceptionTest(){
        String originalUrl = "https://www.naver.com";
        CreateShortenUrlDto createShortenUrlDto = new CreateShortenUrlDto(originalUrl);

        when(shortenUrlRepository.existsShortenUrlByShortUrl(any())).thenReturn(true);

        Assertions.assertThrows(DuplicationShortUrlException.class, () -> {
            shortenUrlService.save(createShortenUrlDto);
        });
    }
}
