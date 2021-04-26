package com.gregpawlaszek.empikapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CalculationRequest {

    private int followersCount;
    private int publicReposCount;

}
