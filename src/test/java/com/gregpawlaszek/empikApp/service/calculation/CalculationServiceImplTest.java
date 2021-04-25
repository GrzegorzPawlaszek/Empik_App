package com.gregpawlaszek.empikApp.service.calculation;

import com.gregpawlaszek.empikApp.dto.CalculationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculationServiceImplTest {

    @Autowired
    private CalculationService service;

    @Test
    void checkCountReposAlgorithm(){
        Assertions.assertAll(
                () -> assertEquals(0, service.countFollowersFactor(new CalculationRequest(0,0))),
                () -> assertEquals(6, service.countFollowersFactor(new CalculationRequest(1,0))),
                () -> assertEquals(1, service.countFollowersFactor(new CalculationRequest(6,0)))
        );
    }

    @Test
    void checkCountPublicReposAlgorithm(){
        Assertions.assertAll(
                () -> assertEquals(2, service.countReposFactor(new CalculationRequest(0,0))),
                () -> assertEquals(3, service.countReposFactor(new CalculationRequest(0,1))),
                () -> assertEquals(10, service.countReposFactor(new CalculationRequest(0,8)))
        );
    }

    @Test
    void checkcalculationAlgorithm(){
        Assertions.assertAll(
                () -> assertEquals(0, service.getCalculation(new CalculationRequest(0,0))),
                () -> assertEquals(12, service.getCalculation(new CalculationRequest(1,0))),
                () -> assertEquals(0, service.getCalculation(new CalculationRequest(0,1))),
                () -> assertEquals(18, service.getCalculation(new CalculationRequest(1,1)))
        );
    }
}