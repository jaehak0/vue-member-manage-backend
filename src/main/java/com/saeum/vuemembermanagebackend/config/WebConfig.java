package com.saeum.vuemembermanagebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.frontend.base-urls:http://localhost:3000}")
    private List<String> frontendBaseUrls;

    @Value("${app.cors.max-age:3600}")
    private int corsMaxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // API 경로에 대해서만 CORS 허용
                .allowedOrigins(frontendBaseUrls.toArray(new String[0])) // yml에서 설정한 URL들
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(corsMaxAge); // preflight 요청 캐시 시간 (1시간)
    }
}