package com.lex.dockersecondservice.service;

import com.lex.dockersecondservice.dto.CalculatorDto;
import com.lex.dockersecondservice.dto.FinalComputationDto;

public interface FinalComputationService {
    public FinalComputationDto getFinalComputation(CalculatorDto calculatorDto);
}
