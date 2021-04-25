package com.gregpawlaszek.empikApp.repository;

import com.gregpawlaszek.empikApp.entity.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRequestRepository extends JpaRepository<ApiRequest, String> {

    ApiRequest getGitRequestByLogin(final String login);

    ApiRequest findRequestByLogin(String userLogin);
}
