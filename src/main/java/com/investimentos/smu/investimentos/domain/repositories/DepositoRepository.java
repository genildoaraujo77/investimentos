package com.investimentos.smu.investimentos.domain.repositories;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.contas.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    List<Deposito> findByConta(Conta conta);
}
