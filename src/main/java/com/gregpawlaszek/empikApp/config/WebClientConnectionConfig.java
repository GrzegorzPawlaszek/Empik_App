package com.gregpawlaszek.empikapp.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@ConfigurationProperties("webclient.connect")
@Service
@Getter
@Setter
public class WebClientConnectionConfig {

    private int timeout;
    private int reply;
    

}
