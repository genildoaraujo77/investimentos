package com.investimentos.smu.investimentos.domain.models.contas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double valorTransferido;

    @NotNull
    private LocalDateTime dataHora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private Conta conta;

}
