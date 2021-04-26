package com.gregpawlaszek.empikapp.controller;

import com.gregpawlaszek.empikapp.entity.ApiRequest;
import com.gregpawlaszek.empikapp.dto.GitUser;
import com.gregpawlaszek.empikapp.dto.User;
import com.gregpawlaszek.empikapp.exception.UserLoginArgumentException;
import com.gregpawlaszek.empikapp.service.git.GitService;
import com.gregpawlaszek.empikapp.service.requestcounter.RequestCounterService;
import com.gregpawlaszek.empikapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final GitService gitService;
    private final RequestCounterService requestCounterService;
    private final UserService userService;

    @GetMapping("/{userLogin}")
    public ResponseEntity<User> getUser(@PathVariable("userLogin") final String userLogin){
        if (userLogin == null || userLogin.isBlank())
            throw new UserLoginArgumentException();

        log.info("Request for user: {}", userLogin);
        requestCounterService.incrementCount(userLogin);

        GitUser gitUser = gitService.getUserByLogin(userLogin);

        User responseUser = userService.createResponseUser(gitUser);
        return ResponseEntity.ok(responseUser);
    }

    @GetMapping("/count/{userLogin}")
    public ResponseEntity<ApiRequest> getCount(@PathVariable("userLogin") final String userLogin){
        ApiRequest apiRequest = requestCounterService.findRequestByLogin(userLogin);
            return ResponseEntity.ok(apiRequest);
    }

}
