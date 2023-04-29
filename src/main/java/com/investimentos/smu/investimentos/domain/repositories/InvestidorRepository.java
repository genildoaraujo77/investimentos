package com.investimentos.smu.investimentos.domain.repositories;

import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.investidores.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestidorRepository extends JpaRepository<Investidor, Long> {
    Investidor findByConta(Conta conta);
}
