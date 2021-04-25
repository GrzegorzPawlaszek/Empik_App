package com.gregpawlaszek.empikApp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculationConfig {

    @Value("${calculation.followers.nominator: 1}")
    private int nominator;

    @Value("${calculation.repos.incrementor: 0}")
    private int incrementor;
}

