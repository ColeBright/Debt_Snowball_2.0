package com.exercise.backend.Services;

import com.exercise.backend.Entities.Debt;
import com.exercise.backend.Repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//TODO add serviceImpl

@Service

public class DebtService {

    @Autowired
    private DebtRepository debtRepository;

    public List<Debt> listAllDebts() {
        return (List<Debt>) debtRepository.findAll();
    }

    public Debt createDebt(Debt debt) {
        return  debtRepository.save(debt);
    }
}
