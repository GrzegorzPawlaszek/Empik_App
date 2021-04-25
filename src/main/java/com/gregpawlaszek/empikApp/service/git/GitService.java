package com.gregpawlaszek.empikApp.service.git;

import com.gregpawlaszek.empikApp.dto.GitUser;
import com.gregpawlaszek.empikApp.exception.ExternalApiException;
import com.gregpawlaszek.empikApp.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
@Slf4j
public class GitService {

    @Value("${github.url}")
    private String githubUrl;

    @Value("${connect.timeout}")
    private int connectTimeout;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public GitUser getUserByLogin(String userLogin){

        try {
            return webClientBuilder.build()
                    .get()
                    .uri(githubUrl, userLogin)
                    .retrieve()
                    .bodyToMono(GitUser.class)
                    .timeout(Duration.ofMillis(connectTimeout))
                    .block();
        } catch (WebClientResponseException ex) {
            log.error(ex.getLocalizedMessage());
            throw new UserNotFoundException(userLogin);
        }
        catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            throw new ExternalApiException();
        }
    }
}
