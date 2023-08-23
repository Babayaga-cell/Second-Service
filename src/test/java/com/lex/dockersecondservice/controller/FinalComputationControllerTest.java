package com.lex.dockersecondservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.dockersecondservice.dto.CalculatorDto;
import com.lex.dockersecondservice.dto.FinalComputationDto;
import com.lex.dockersecondservice.service.FinalComputationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FinalComputationControllerTest {

    private MockMvc mockMvc;

    private FinalComputationService finalComputationService;

    private CalculatorDto calculatorDto;

    private FinalComputationDto finalComputationDto;

    private String requestBody;


    @BeforeEach
    void setUp() throws Exception{
        finalComputationService = mock(FinalComputationService.class);
        FinalComputationController finalComputationController = new FinalComputationController(finalComputationService);
        mockMvc = MockMvcBuilders.standaloneSetup(finalComputationController).build();

        calculatorDto = new CalculatorDto();
        calculatorDto.setProductName("sample");
        calculatorDto.setPrice(3.0);
        calculatorDto.setUnit(2.0);

        finalComputationDto = new FinalComputationDto();
        finalComputationDto.setProductName("sample");
        finalComputationDto.setFinalPrice(6.0);

        ObjectMapper objectMapper = new ObjectMapper();
        requestBody = objectMapper.writeValueAsString(calculatorDto);

    }

    @Test
    void computeFinalPrice() throws Exception{
        when(finalComputationService.getFinalComputation(any(CalculatorDto.class))).thenReturn(finalComputationDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/second/calculate/finalComputation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("sample"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value("6.0"));

        // Verify that the service method was called
        verify(finalComputationService, times(1)).getFinalComputation(any(CalculatorDto.class));

    }

    @Test
    void computeFinalPriceIsBadRequest() throws Exception{
        when(finalComputationService.getFinalComputation(any(CalculatorDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/second/calculate/finalComputation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(finalComputationService, times(0)).getFinalComputation(any(CalculatorDto.class));

    }
}