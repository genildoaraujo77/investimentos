package com.investimentos.smu.investimentos.domain.models.contas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "saques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Saque {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Valor obrigatório")
    private Double valorSacado;

    @NotNull
    private LocalDateTime dataHora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private Conta conta;

}
