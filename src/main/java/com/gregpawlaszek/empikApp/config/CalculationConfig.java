package com.gregpawlaszek.empikapp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CalculationConfig {

    @Value("${calculation.followers.nominator: 1}")
    private int nominator;

    @Value("${calculation.repos.incrementor: 0}")
    private int incrementor;
}

