package com.investimentos.smu.investimentos.domain.services;

import com.investimentos.smu.investimentos.domain.dtos.ContaDto;
import com.investimentos.smu.investimentos.domain.dtos.InvestidorDto;
import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.investidores.Investidor;
import com.investimentos.smu.investimentos.domain.repositories.InvestidorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestidorService {

    private InvestidorRepository repository;

    private ContaService serviceConta;

    private ModelMapper modelMapper;

    @Autowired
    public InvestidorService(InvestidorRepository repository, ContaService serviceConta, ModelMapper modelMapper) {
        this.repository = repository;
        this.serviceConta = serviceConta;
        this.modelMapper = modelMapper;
    }

    public InvestidorDto salvaInvestidorECriaConta(InvestidorDto dto) {

        Investidor investidor = modelMapper.map(dto, Investidor.class);
        Investidor investidorSalvo = repository.save(investidor);

        Conta contaSalva = serviceConta.criarConta(investidorSalvo);

        investidorSalvo.setConta(contaSalva);
        repository.save(investidorSalvo);
        ContaDto contaDto = modelMapper.map(contaSalva, ContaDto.class);
        InvestidorDto investidorDto = modelMapper.map(investidorSalvo, InvestidorDto.class);
        investidorDto.setConta(contaDto);

        return investidorDto;

    }
}
