package com.investimentos.smu.investimentos.domain.dtos;

import com.investimentos.smu.investimentos.domain.models.types.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDto {

    private String numero;
    private String agencia;
    private double saldo;
    private Status status;


}
