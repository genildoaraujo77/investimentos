package com.investimentos.smu.investimentos.domain.repositories;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.contas.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByConta(Conta conta);
}
