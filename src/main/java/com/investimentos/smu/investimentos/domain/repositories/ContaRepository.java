package com.investimentos.smu.investimentos.domain.repositories;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByNumero(String numeroConta);
}
