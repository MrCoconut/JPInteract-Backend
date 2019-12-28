package com.newjpinteract.jpinteract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class JpinteractApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(JpinteractApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // Grant CORS permission to response header
        Cors corsRegistration = new Cors("/**");
        corsRegistration.allowedOrigins("*")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(),
                        HttpMethod.PUT.name(), HttpMethod.OPTIONS.name())
                .allowedHeaders("Accept", "Origin", "X-Requested-With", "Content-Type",
                        "Last-Modified", "device", "token")
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .allowCredentials(true)
                .maxAge(3600);

        // Register CORS Filter
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return new FilterRegistrationBean(corsFilter);
    }
}
