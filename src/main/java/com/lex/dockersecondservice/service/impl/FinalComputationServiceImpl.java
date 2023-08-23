package com.lex.dockersecondservice.service.impl;

import com.lex.dockersecondservice.dto.CalculatorDto;
import com.lex.dockersecondservice.dto.FinalComputationDto;
import com.lex.dockersecondservice.service.FinalComputationService;
import org.springframework.stereotype.Service;

@Service
public class FinalComputationServiceImpl implements FinalComputationService {
    @Override
    public FinalComputationDto getFinalComputation(CalculatorDto calculatorDto) {
        FinalComputationDto finalComputationDto = new FinalComputationDto();
        double finalPrice = calculatorDto.getPrice() * calculatorDto.getUnit();

        finalComputationDto.setProductName(calculatorDto.getProductName());
        finalComputationDto.setFinalPrice(finalPrice);

        return finalComputationDto;
    }
}
