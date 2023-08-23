package com.lex.dockersecondservice.service.impl;

import com.lex.dockersecondservice.dto.CalculatorDto;
import com.lex.dockersecondservice.dto.FinalComputationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalComputationServiceImplTest {

    private FinalComputationServiceImpl finalComputationService;

    private CalculatorDto calculatorDto;

    private FinalComputationDto finalComputationDto;

    @BeforeEach
    void setUp() {
        finalComputationService = new FinalComputationServiceImpl();

        calculatorDto = new CalculatorDto();
        calculatorDto.setProductName("sample");
        calculatorDto.setPrice(3.0);
        calculatorDto.setUnit(2.0);

        finalComputationDto = new FinalComputationDto();
        finalComputationDto.setProductName("sample");
        finalComputationDto.setFinalPrice(6.0);

    }

    @Test
    void getFinalComputation() {

        // Call the method under test
        FinalComputationDto response = finalComputationService.getFinalComputation(calculatorDto);

        assertNotNull(response);
        assertEquals(calculatorDto.getPrice()*calculatorDto.getUnit(), response.getFinalPrice());
    }
}