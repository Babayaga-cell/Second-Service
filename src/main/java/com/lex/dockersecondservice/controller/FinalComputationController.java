package com.lex.dockersecondservice.controller;

import com.lex.dockersecondservice.dto.CalculatorDto;
import com.lex.dockersecondservice.dto.FinalComputationDto;
import com.lex.dockersecondservice.service.FinalComputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("second/calculate")
public class FinalComputationController {
    private final FinalComputationService finalComputationService;

    @Autowired
    public FinalComputationController(FinalComputationService finalComputationService){
        this.finalComputationService = finalComputationService;
    }

    @PostMapping("/finalComputation")
    public FinalComputationDto postFinalComputation(@RequestBody CalculatorDto calculatorDto){
        return finalComputationService.getFinalComputation(calculatorDto);
    }
}
