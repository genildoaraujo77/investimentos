package com.investimentos.smu.investimentos.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.investimentos.smu.investimentos.domain.dtos.InvestidorDto;
import com.investimentos.smu.investimentos.domain.repositories.InvestidorRepository;
import com.investimentos.smu.investimentos.domain.services.ContaService;
import com.investimentos.smu.investimentos.domain.services.InvestidorService;
import com.investimentos.smu.investimentos.utils.AbstractReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class InvestidorServiceTest {
    @Mock
    private InvestidorService investidorService;

    @Mock
    private InvestidorRepository repository;

    @Mock
    private ContaService serviceConta;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        repository.deleteAll();
    }

    @Test
    void deveriaSalvarInvestidorECriarConta() {

        InvestidorDto investidorDtoInsert = null;
        InvestidorDto investidorDtoResult = null;
        try {
            investidorDtoInsert = retornaJson("investidorDtoInsert.json");
            investidorDtoResult = retornaJson("investidorDtoResult.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Mockito.when(investidorService.salvaInvestidorECriaConta(investidorDtoInsert)).thenReturn(investidorDtoResult);

        InvestidorDto investidorDto = investidorService.salvaInvestidorECriaConta(investidorDtoInsert);
        assertEquals(1L, investidorDto.getId());
        assertEquals("Genildo", investidorDto.getNome());
        assertEquals("000001", investidorDto.getConta().getNumero());

        verify(investidorService).salvaInvestidorECriaConta(investidorDtoInsert);

    }

    private InvestidorDto retornaJson(String resourceName) throws IOException {
            String jsonText = AbstractReader.readJson(resourceName);
            Type collectionType = new TypeToken<InvestidorDto>(){}.getType();
            return new Gson().fromJson(jsonText,collectionType);
    }

}
