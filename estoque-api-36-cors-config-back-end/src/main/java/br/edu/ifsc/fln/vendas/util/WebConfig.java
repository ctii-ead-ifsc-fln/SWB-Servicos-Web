package br.edu.ifsc.fln.vendas.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOriginPatterns("http://localhost:8080", "http://localhost", "*") // Adicione os domínios permitidos
        		.allowedOriginPatterns("*") // Adicione os domínios permitidos
        		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        		.allowCredentials(false)
                .allowedHeaders("*");
    }
}

