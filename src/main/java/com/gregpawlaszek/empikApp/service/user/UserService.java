package com.gregpawlaszek.empikapp.service.user;

import com.gregpawlaszek.empikapp.dto.CalculationRequest;
import com.gregpawlaszek.empikapp.dto.GitUser;
import com.gregpawlaszek.empikapp.dto.User;
import com.gregpawlaszek.empikapp.service.calculation.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CalculationService calculationService;

    public User createResponseUser(GitUser gitUser) {
        double calculation = getCalculation(gitUser);
        return new User(
                gitUser.getId(),
                gitUser.getLogin(),
                gitUser.getName(),
                gitUser.getType(),
                gitUser.getAvatar_url(),
                gitUser.getCreated_at(),
                calculation
        );
    }

    public double getCalculation(GitUser gitUser) {
        CalculationRequest calcRequest = createCalcRequest(gitUser);
        return calculationService.getCalculation(calcRequest);
    }

    private CalculationRequest createCalcRequest(GitUser gitUser) {
        return CalculationRequest.builder()
                .followersCount(gitUser.getFollowers())
                .publicReposCount(gitUser.getPublic_repos())
                .build();
    }
}
