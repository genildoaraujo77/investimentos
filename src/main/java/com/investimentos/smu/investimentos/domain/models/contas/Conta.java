package com.investimentos.smu.investimentos.domain.models.contas;

import com.investimentos.smu.investimentos.domain.models.investidores.Investidor;
import com.investimentos.smu.investimentos.domain.models.types.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @NotNull(message = "Agência é obrigatório")
    private String agencia;
    private double saldo;
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_investidor", referencedColumnName = "id")
    private Investidor investidor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_deposito", referencedColumnName = "id")
    private Deposito deposito;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_saque", referencedColumnName = "id")
    private Saque saque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transferencia", referencedColumnName = "id")
    private Transferencia transferencia;

    @NotNull
    private LocalDateTime dataHora;

    public boolean depositar(double valor) {
        saldo = saldo + valor;
        return true;
    }

    public boolean sacar(double valor){
        if ((saldo - valor) >= 0){
            saldo -= valor;
            return true;
        }
        return false;
    }
}
