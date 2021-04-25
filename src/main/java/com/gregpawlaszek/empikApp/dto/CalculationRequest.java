package com.gregpawlaszek.empikApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculationRequest {

    private int followersCount;
    private int publicReposCount;

}
