package com.clientside.mbkm.Configs;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LayoutDialectConfig {
    @Bean
    public LayoutDialect LayoutDialect() {
        return new LayoutDialect();
    }
}
