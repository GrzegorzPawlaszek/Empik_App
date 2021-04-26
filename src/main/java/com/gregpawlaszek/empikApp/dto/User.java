package com.gregpawlaszek.empikapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDate createdAt;
    private double calculations;
}
