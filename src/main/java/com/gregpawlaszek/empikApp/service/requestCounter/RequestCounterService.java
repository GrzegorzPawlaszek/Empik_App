package com.gregpawlaszek.empikapp.service.requestcounter;

import com.gregpawlaszek.empikapp.entity.ApiRequest;
import com.gregpawlaszek.empikapp.repository.GitRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestCounterService {

    @Autowired
    private GitRequestRepository gitRequestRepository;

    public ApiRequest incrementCount(String userLogin) {
        ApiRequest apiRequest = getGitRequestByLogin(userLogin);

        if (apiRequest != null) {
            long requestCount = apiRequest.getRequestCount();
            apiRequest.setRequestCount(++requestCount);
        } else {
            apiRequest = new ApiRequest(userLogin, 1);
        }

        return saveGitRequest(apiRequest);
    }
    
    private ApiRequest getGitRequestByLogin(String userLogin){
        return gitRequestRepository.getGitRequestByLogin(userLogin);
    }

    private ApiRequest saveGitRequest(ApiRequest userRequest){
        return gitRequestRepository.save(userRequest);
    }

    public ApiRequest findRequestByLogin(String userLogin) {
        return gitRequestRepository.findRequestByLogin(userLogin);
    }
}
