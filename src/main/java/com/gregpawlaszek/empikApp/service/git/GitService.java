package com.gregpawlaszek.empikapp.service.git;

import com.gregpawlaszek.empikapp.config.WebClientConnectionConfig;
import com.gregpawlaszek.empikapp.dto.GitUser;
import com.gregpawlaszek.empikapp.exception.ExternalApiException;
import com.gregpawlaszek.empikapp.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitService {

    @Value("${github.url}")
    private String githubUrl;

    private final WebClientConnectionConfig connectionConfig;

    private final WebClient.Builder webClientBuilder;

    public GitUser getUserByLogin(String userLogin){

        try {
            return webClientBuilder.build()
                    .get()
                    .uri(githubUrl, userLogin)
                    .retrieve()
                    .bodyToMono(GitUser.class)
                    .retryWhen(Retry.backoff(connectionConfig.getReply(), Duration.ofMillis(connectionConfig.getTimeout()))
                            .filter(ex -> isExternalException(ex))
                            .onRetryExhaustedThrow((retryBackoffSpec, retrySignel) -> {
                                log.error(retrySignel.failure().getLocalizedMessage());
                                return new ExternalApiException();
                            }))
                    .block();
        } catch (WebClientResponseException ex) {
            log.error(ex.getLocalizedMessage());
            throw new UserNotFoundException(userLogin);
        }
    }
    
    public static boolean isExternalException(Throwable ex){
        return ex instanceof WebClientRequestException;
    }

}
