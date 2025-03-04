package com.example.pdf_demo.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafConfig {

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {

        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();

        emailTemplateResolver.setPrefix("templates/");

        emailTemplateResolver.setTemplateMode("HTML5");

        emailTemplateResolver.setSuffix(".html");

        emailTemplateResolver.setTemplateMode("XHTML");

        emailTemplateResolver.setCharacterEncoding("UTF-8");

        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }
}
