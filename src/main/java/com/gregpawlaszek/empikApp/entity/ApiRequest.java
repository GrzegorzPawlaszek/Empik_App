package com.gregpawlaszek.empikapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REQUEST")
public class ApiRequest {

    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_LOGIN")
    private long requestCount;
}
