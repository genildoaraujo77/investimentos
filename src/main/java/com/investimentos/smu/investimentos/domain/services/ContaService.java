package com.investimentos.smu.investimentos.domain.services;

import com.investimentos.smu.investimentos.domain.dtos.*;
import com.investimentos.smu.investimentos.domain.models.contas.Conta;
import com.investimentos.smu.investimentos.domain.models.contas.Deposito;
import com.investimentos.smu.investimentos.domain.models.contas.Saque;
import com.investimentos.smu.investimentos.domain.models.contas.Transferencia;
import com.investimentos.smu.investimentos.domain.models.investidores.Investidor;
import com.investimentos.smu.investimentos.domain.models.types.Status;
import com.investimentos.smu.investimentos.domain.repositories.*;
import com.investimentos.smu.investimentos.domain.utils.GeraNumeros;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {
    @Autowired
    private ContaRepository repository;

    @Autowired
    InvestidorRepository repositoryInvestidor;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private SaqueRepository saqueRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ModelMapper modelMapper;
    public Conta criarConta(Investidor investidor) {

        Conta conta = new Conta();
        conta.setAgencia("001");
        Integer qtdContas = Math.toIntExact(repository.count());
        qtdContas += 1;
        conta.setNumero(GeraNumeros.geraNumeroConta((qtdContas)));
        conta.setDataHora(LocalDateTime.now());
        conta.setStatus(Status.ABERTO);
        conta.setSaldo(0.00);
        conta.setInvestidor(investidor);
        Conta contaSalva = repository.save(conta);

        return contaSalva;
    }

    //    Depósito
    public ContaDto depositar(double valor, String numeroConta){
        Conta conta = repository.findByNumero(numeroConta);

        if (depositoIsValid(valor, conta)){
            Deposito newDeposito = new Deposito(null, valor, LocalDateTime.now(), conta);
            Deposito deposito = depositoRepository.save(newDeposito);
            conta.setDeposito(deposito);
            repository.save(conta);
        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(conta, ContaDto.class);
    }

    private static boolean depositoIsValid(double valor, Conta conta) {
        return conta != null && conta.depositar(valor);
    }

    //    Saque
    public ContaDto sacar(double valor, String numeroConta){
        Conta conta = repository.findByNumero(numeroConta);

        if (saqueIsValid(valor, conta)){
            Saque newSaque = new Saque(null, valor, LocalDateTime.now(), conta);
            Saque saque = saqueRepository.save(newSaque);
            conta.setSaque(saque);
            repository.save(conta);
        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(conta, ContaDto.class);
    }

    private static boolean saqueIsValid(double valor, Conta conta) {
        return conta != null && conta.sacar(valor);
    }

    //    Transferência
    public ContaDto transferir(double valor, String numeroConta, String numeroContaTerceiro){
        Conta conta = repository.findByNumero(numeroConta);
        Conta contaTerceiro = repository.findByNumero(numeroContaTerceiro);

        if (isValid(valor, conta, contaTerceiro)){
            this.depositar(valor, contaTerceiro.getNumero());
            Transferencia newTransfer = new Transferencia(null, valor, LocalDateTime.now(), conta);
            Transferencia transferencia = transferenciaRepository.save(newTransfer);
            conta.setTransferencia(transferencia);

            List<Conta> contas = new ArrayList<>();
            contas.add(conta);
            contas.add(contaTerceiro);

            repository.saveAll(contas);

        } else {
            throw new EntityNotFoundException();
        }

        return modelMapper.map(conta, ContaDto.class);
    }

    private static boolean isValid(double valor, Conta conta, Conta contaTerceiro) {
        return (conta != null || contaTerceiro != null) && Objects.requireNonNull(conta).sacar(valor) && conta != contaTerceiro;
    }

    //    extrato
    public ExtratoDto extrato(String numeroConta){
        Conta conta = repository.findByNumero(numeroConta);

        if (conta == null) {
            throw new EntityNotFoundException();
        }

        ContaDto contaDto = modelMapper.map(conta, ContaDto.class);
        ExtratoDto extratoDto = new ExtratoDto();
        extratoDto.setConta(contaDto);
        extratoDto.setDepositos(obterDepositosPorConta(conta));
        extratoDto.setSaques(obterSaquesPorConta(conta));
        extratoDto.setTransferencias(obterTransferenciasPorConta(conta));

        return extratoDto;
    }

    private List<TransferenciaDto> obterTransferenciasPorConta(Conta conta) {
        List<Transferencia> transferencia = transferenciaRepository.findByConta(conta);
        return transferencia.stream().map(s -> modelMapper.map(s, TransferenciaDto.class))
                .collect(Collectors.toList());
    }

    private List<SaqueDto> obterSaquesPorConta(Conta conta) {
        List<Saque> saques = saqueRepository.findByConta(conta);
        return saques.stream().map(s -> modelMapper.map(s, SaqueDto.class))
                .collect(Collectors.toList());
    }

    public List<DepositoDto> obterDepositosPorConta(Conta conta) {
        List<Deposito> depositos = depositoRepository.findByConta(conta);
        return depositos.stream().map(d -> modelMapper.map(d, DepositoDto.class))
                .collect(Collectors.toList());
    }

}
