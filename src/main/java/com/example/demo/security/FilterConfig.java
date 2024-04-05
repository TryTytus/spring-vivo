package com.example.demo.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class FilterConfig {

//    @Autowired
    private jwtAuthFilter jwtAuthFilter;

    @Autowired
    public FilterConfig(jwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public FilterRegistrationBean<jwtAuthFilter> loggingFilter()
    {
        FilterRegistrationBean<jwtAuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(jwtAuthFilter);
        registrationBean.addUrlPatterns("/**"); // URL pattern for API endpoints

        return registrationBean;
    }
}
