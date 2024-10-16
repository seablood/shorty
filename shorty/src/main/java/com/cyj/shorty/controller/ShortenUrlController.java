package com.cyj.shorty.controller;

import com.cyj.shorty.dto.CreateShortenUrlDto;
import com.cyj.shorty.dto.ShortenUrlDto;
//import com.cyj.shorty.service.ShortenUrlMapService;
import com.cyj.shorty.service.ShortenUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {
    private final ShortenUrlService shortenUrlService;

    @PostMapping("/create-shorten-url")
    public ResponseEntity<ShortenUrlDto> createShortenUrl(@Valid @RequestBody CreateShortenUrlDto createShortenUrlDto){
        ShortenUrlDto shortenUrlDto = shortenUrlService.save(createShortenUrlDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenUrlDto);
    }

    @GetMapping("/{shortenUrl}")
    public ResponseEntity<?> redirectUrl(@PathVariable String shortenUrl){
        String originalUrl = shortenUrlService.getOriginalUrl(shortenUrl);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Location", originalUrl);

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/shorten-url-info/{shortenUrl}")
    public ResponseEntity<ShortenUrlDto> getInfo(@PathVariable String shortenUrl){
        ShortenUrlDto shortenUrlDto = shortenUrlService.getShortenUrlInfo(shortenUrl);
        return ResponseEntity.status(HttpStatus.OK).body(shortenUrlDto);
    }
}
