package com.authentication.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceBeanManager {

    @Bean
    public APIResponse response(){
        return new APIResponse();
    }
}
