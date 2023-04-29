package com.investimentos.smu.investimentos.domain.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    @Bean
    public ModelMapper modelMapper() {
//        var modelMapper = new ModelMapper();
//
//        var investidorDtoToInvestidorTypeMap = modelMapper.createTypeMap(
//                InvestidorDto.class, Investidor.class);
//
//        investidorDtoToInvestidorTypeMap.<Tipos>addMapping(
//                investidorSrc -> investidorSrc.getTipo(),
//                (investidorDest, value) -> investidorDest.setTipo(value));
//
//        return modelMapper;
        return new ModelMapper();
    }
}
