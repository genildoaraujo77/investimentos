package com.investimentos.smu.investimentos.domain.repositories;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.contas.Saque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaqueRepository extends JpaRepository<Saque, Long> {
    List<Saque> findByConta(Conta conta);
}
