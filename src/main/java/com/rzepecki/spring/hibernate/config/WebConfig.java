package com.rzepecki.spring.hibernate.config;

import com.rzepecki.spring.hibernate.converters.PublisherConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


    //add converter bean
    @Bean
    public PublisherConverter publisherConverter(){
        return new PublisherConverter();
    }

    //register converter
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(publisherConverter());
    }
}
