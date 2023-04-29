package com.investimentos.smu.investimentos.domain.models.investidores;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.types.Tipos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "investidores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investidor implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String cpfCnpj;
    @NotNull @Enumerated(EnumType.STRING)
    private Tipos tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta", referencedColumnName = "id")
    private Conta conta;
}
