package com.investimentos.smu.investimentos.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDto implements Serializable {

    private Long id;

    private Double valorTransferido;

    private LocalDateTime dataHora;

    private ContaDto conta;
}
