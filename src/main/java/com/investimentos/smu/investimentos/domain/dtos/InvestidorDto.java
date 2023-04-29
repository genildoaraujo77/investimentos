package com.investimentos.smu.investimentos.domain.dtos;

import com.investimentos.smu.investimentos.domain.models.types.Tipos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestidorDto implements Serializable {

    private Long id;
    private String nome;
    private String cpfCnpj;
    private Tipos tipo;
    private ContaDto conta;

}
