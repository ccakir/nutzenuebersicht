package com.cakirilhan.Ort;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.cakirilhan.domain.kunde.service.impl.OrtServiceImpl;

@Profile("test")
@Configuration
public class NameServiceTestConfiguration {
    @Bean
    @Primary
    public OrtServiceImpl ortService() {
        return Mockito.mock(OrtServiceImpl.class);
    }
}