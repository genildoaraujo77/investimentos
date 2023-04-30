package com.investimentos.smu.investimentos.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.investimentos.smu.investimentos.domain.dtos.ContaDto;
import com.investimentos.smu.investimentos.domain.dtos.InvestidorDto;
import com.investimentos.smu.investimentos.domain.models.types.Status;
import com.investimentos.smu.investimentos.domain.repositories.*;
import com.investimentos.smu.investimentos.domain.services.ContaService;
import com.investimentos.smu.investimentos.domain.services.InvestidorService;
import com.investimentos.smu.investimentos.utils.AbstractReader;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class ContaServiceTest {
    @Mock
    private ContaRepository repository;

    @Mock
    private ContaService serviceConta;

    @Mock
    InvestidorRepository repositoryInvestidor;

    @Mock
    private DepositoRepository depositoRepository;

    @Mock
    private SaqueRepository saqueRepository;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveriaRealizarDepositoNaConta() {

        ContaDto contaDtoResult = null;
        try {
            contaDtoResult = retornaJson("depositoContaResult.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Mockito.when(serviceConta.depositar(100.00, "000001")).thenReturn(contaDtoResult);

        ContaDto contaDto = serviceConta.depositar(100.00, "000001");
        assertEquals("000001", contaDto.getNumero());
        assertEquals(100.0, contaDto.getSaldo());

        verify(serviceConta).depositar(100.00, "000001");

    }

    @Test
    void deveriaRealizarSaqueNaConta() {

        serviceConta.depositar(100.00, "000001");

        Mockito.when(serviceConta.sacar(89.00, "000001"))
                .thenReturn(new ContaDto("000001", "001", 11.0, Status.ABERTO));

        ContaDto contaDto = serviceConta.sacar(89.00, "000001");
        assertEquals("000001", contaDto.getNumero());
        assertEquals(11.0, contaDto.getSaldo());

        verify(serviceConta).sacar(89.00, "000001");

    }

    @Test
    void deveriaRealizartransferenciaParaOutraConta() {

        serviceConta.depositar(100.00, "000001");

        Mockito.when(serviceConta.transferir(60.00, "000001", "000002"))
                .thenReturn(new ContaDto("000001", "001", 40.0, Status.ABERTO));

        ContaDto contaDto = serviceConta.transferir(60.00, "000001", "000002");
        assertEquals("000001", contaDto.getNumero());
        assertEquals(40.0, contaDto.getSaldo());

        verify(serviceConta).transferir(60.00, "000001", "000002");

    }

    @Test
    void naoDeveriaRealizartransferenciaParaPropriaConta() {

        serviceConta.depositar(100.00, "000001");

        Mockito.when(serviceConta.transferir(60.00, "000001", "000001"))
                .thenThrow(EntityNotFoundException.class);

                assertThrows(EntityNotFoundException.class, () ->
                serviceConta.transferir(60.00, "000001", "000001"));

        verify(serviceConta).transferir(60.00, "000001", "000001");

    }

    private ContaDto retornaJson(String resourceName) throws IOException {
            String jsonText = AbstractReader.readJson(resourceName);
            Type collectionType = new TypeToken<ContaDto>(){}.getType();
            return new Gson().fromJson(jsonText,collectionType);
    }

}
