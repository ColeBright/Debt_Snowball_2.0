package com.exercise.backend.web;

import com.exercise.backend.Entities.Debt;
import com.exercise.backend.Repository.DebtRepository;
import com.exercise.backend.Services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
public class DebtController {
    @Autowired
    private DebtRepository debtRepository;
//    private DebtService debtService;
//    @Autowired
//    public void setDebtService(DebtService debtService) {
//        this.debtService = debtService;
//    }
    @GetMapping("")
    public List<Debt> retrieveDebts(){
        return debtRepository.findAll();
    }
    @PostMapping("")
    public Debt createDebt(@RequestBody Debt debt) {
        return debtRepository.save(debt);
    }



}
