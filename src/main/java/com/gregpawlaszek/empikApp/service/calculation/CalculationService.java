package com.gregpawlaszek.empikApp.service.calculation;

import com.gregpawlaszek.empikApp.dto.CalculationRequest;

public interface CalculationService {


    int countReposFactor(CalculationRequest calculationRequest);
    double countFollowersFactor(CalculationRequest calculationRequest);

    default double getCalculation(CalculationRequest calculationRequest){

        double followersFactor = countFollowersFactor(calculationRequest);
        int publicReposFactor = countReposFactor(calculationRequest);

        double calculation = followersFactor * publicReposFactor;

        return calculation;
    }
}
