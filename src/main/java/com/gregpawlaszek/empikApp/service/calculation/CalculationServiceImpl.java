package com.gregpawlaszek.empikApp.service.calculation;

import com.gregpawlaszek.empikApp.config.CalculationConfig;
import com.gregpawlaszek.empikApp.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService{

    private final CalculationConfig calculationConfig;

    public CalculationServiceImpl(CalculationConfig calculationConfig){
        this.calculationConfig = calculationConfig;
    }

    public int countReposFactor(CalculationRequest calculationRequest) {
        int publicReposCount = calculationRequest.getPublicReposCount();
        int incrementor = calculationConfig.getIncrementor();

        return publicReposCount + incrementor;
    }

    public double countFollowersFactor(CalculationRequest calculationRequest) {
        int followersCount = calculationRequest.getFollowersCount();
        int nominator = calculationConfig.getNominator();

        return followersCount == 0 ? 0 : nominator / followersCount;

    }


}
