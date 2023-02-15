package org.hoshino.miraiclient4j.config;

import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    @ConditionalOnProperty(prefix = "mirai.config",name = "api-adapter", havingValue = "http")
    public HttpApiClient httpApiClient(){
        return new HttpApiClient();
    }

}
