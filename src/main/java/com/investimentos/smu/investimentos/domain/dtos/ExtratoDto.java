package com.investimentos.smu.investimentos.domain.dtos;

import com.investimentos.smu.investimentos.domain.models.contas.Deposito;
import com.investimentos.smu.investimentos.domain.models.contas.Saque;
import com.investimentos.smu.investimentos.domain.models.contas.Transferencia;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExtratoDto implements Serializable {
    private ContaDto conta;
    private List<DepositoDto> depositos = new ArrayList<>();
    private List<SaqueDto> saques = new ArrayList<>();
    private List<TransferenciaDto> transferencias = new ArrayList<>();
}
