package com.cyj.shorty.dto;

import com.cyj.shorty.domain.ShortenUrl;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortenUrlDto {
    @NotBlank
    @URL(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String originalUrl;

    public static ShortenUrl toEntity(CreateShortenUrlDto createShortenUrlDto){
        return com.cyj.shorty.domain.ShortenUrl.builder()
                .originalUrl(createShortenUrlDto.getOriginalUrl())
                .build();
    }
}
