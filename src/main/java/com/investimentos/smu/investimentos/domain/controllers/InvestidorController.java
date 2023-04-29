package com.investimentos.smu.investimentos.domain.controllers;

import com.investimentos.smu.investimentos.domain.dtos.InvestidorDto;
import com.investimentos.smu.investimentos.domain.services.InvestidorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investidores")
public class InvestidorController {

    @Autowired
    private InvestidorService investidorService;

    @PostMapping
    public InvestidorDto salvar(@RequestBody @Valid InvestidorDto investidorDto){
        return investidorService.salvaInvestidorECriaConta(investidorDto);
    }
}
