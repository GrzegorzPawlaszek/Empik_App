package com.gregpawlaszek.empikapp.service.calculation;

import com.gregpawlaszek.empikapp.config.CalculationConfig;
import com.gregpawlaszek.empikapp.dto.CalculationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalculationServiceImpl implements CalculationService{

    private final CalculationConfig calculationConfig;

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
