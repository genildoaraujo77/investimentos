package com.investimentos.smu.investimentos.domain.controllers;

import com.investimentos.smu.investimentos.domain.dtos.ContaDto;
import com.investimentos.smu.investimentos.domain.dtos.DepositoDto;
import com.investimentos.smu.investimentos.domain.dtos.ExtratoDto;
import com.investimentos.smu.investimentos.domain.dtos.InvestidorDto;
import com.investimentos.smu.investimentos.domain.models.contas.Deposito;
import com.investimentos.smu.investimentos.domain.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping(value = "/{valor}/{numeroConta}")
    public ContaDto depositar(@PathVariable double valor, @PathVariable String numeroConta){
        return contaService.depositar(valor, numeroConta);
    }

    @PostMapping(value = "/sacar/{valor}/{numeroConta}")
    public ContaDto sacar(@PathVariable double valor, @PathVariable String numeroConta){
        return contaService.sacar(valor, numeroConta);
    }

    @PostMapping(value = "/{numeroConta}/transferir/{valor}/{numeroContaTerceiro}")
    public ContaDto transferir(@PathVariable double valor, @PathVariable String numeroConta, @PathVariable String numeroContaTerceiro){
        return contaService.transferir(valor, numeroConta, numeroContaTerceiro);
    }

    @GetMapping(value = "/extrato/{numeroConta}")
    public ExtratoDto extrado(@PathVariable String numeroConta){
        return contaService.extrato(numeroConta);
    }


//    @GetMapping(value = "/testes/{numeroConta}")
//    public List<DepositoDto> depositos(@PathVariable String numeroConta){
//        return contaService.depositos(numeroConta);
//    }
}
