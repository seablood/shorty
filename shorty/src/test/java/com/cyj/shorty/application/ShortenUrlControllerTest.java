package com.cyj.shorty.application;

import com.cyj.shorty.controller.ShortenUrlController;
//import com.cyj.shorty.service.ShortenUrlMapService;
import com.cyj.shorty.service.ShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
@WebMvcTest(controllers = ShortenUrlController.class)
class ShortenUrlControllerTest {
    @MockBean
    private ShortenUrlService shortenUrlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("단축 URL 페이지 리다이렉트 테스트")
    public void redirectTest() throws Exception {
        String originalUrl = "https://www.naver.com";

        when(shortenUrlService.getOriginalUrl(any())).thenReturn(originalUrl);

        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", originalUrl));
    }

}