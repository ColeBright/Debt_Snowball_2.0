package com.exercise.backend.web;

import com.exercise.backend.Entities.Debt;
import com.exercise.backend.Services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debts")
public class DebtController {
    private DebtService debtService;
    @Autowired
    public void setDebtService(DebtService debtService) {
        this.debtService = debtService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Debt>> retrieveDebts(){
        List<Debt> list = debtService.listAllDebts();
        return new ResponseEntity<List<Debt>>(list, HttpStatus.OK);
    }
    @PutMapping("/")
    public Debt createDebt(@RequestBody Debt debt) {
        return debtService.createDebt(debt);
    }



}
