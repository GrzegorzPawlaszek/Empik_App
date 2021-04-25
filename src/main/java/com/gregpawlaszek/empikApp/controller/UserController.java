package com.gregpawlaszek.empikApp.controller;

import com.gregpawlaszek.empikApp.entity.ApiRequest;
import com.gregpawlaszek.empikApp.dto.CalculationRequest;
import com.gregpawlaszek.empikApp.dto.GitUser;
import com.gregpawlaszek.empikApp.dto.User;
import com.gregpawlaszek.empikApp.exception.UserLoginArgumentException;
import com.gregpawlaszek.empikApp.service.git.GitService;
import com.gregpawlaszek.empikApp.service.calculation.CalculationService;
import com.gregpawlaszek.empikApp.service.requestCounter.RequestCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final GitService gitService;
    
    private RequestCounterService requestCounterService;

    private CalculationService calculationService;

    public UserController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/{userLogin}")
    public ResponseEntity<User> getUser(@PathVariable("userLogin") final String userLogin){
        if (userLogin == null || userLogin.isBlank())
            throw new UserLoginArgumentException();

        log.info("Request for user: {}", userLogin);

        GitUser gitUser = gitService.getUserByLogin(userLogin);
        requestCounterService.incrementCount(userLogin);

        User reponseUser = createResponseUser(gitUser);
        return ResponseEntity.ok(reponseUser);
    }

    private User createResponseUser(GitUser gitUser) {
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

    private double getCalculation(GitUser gitUser) {
        CalculationRequest calcRequest = createCalcRequest(gitUser);

        return calculationService.getCalculation(calcRequest);
    }

    private CalculationRequest createCalcRequest(GitUser gitUser) {
        return CalculationRequest.builder()
                .followersCount(gitUser.getFollowers())
                .publicReposCount(gitUser.getPublic_repos())
                .build();
    }

    @GetMapping("/count/{userLogin}")
    public long getCount(@PathVariable("userLogin") final String userLogin){
        ApiRequest apiRequest = requestCounterService.findRequestByLogin(userLogin);
            return apiRequest.getRequestCount();
    }

    @Autowired
    public void setRequestCounterService(RequestCounterService requestCounterService){
        this.requestCounterService = requestCounterService;
    }

    @Autowired
    public void setCalculationService(CalculationService calculationService) {
        this.calculationService = calculationService;
    }
}
