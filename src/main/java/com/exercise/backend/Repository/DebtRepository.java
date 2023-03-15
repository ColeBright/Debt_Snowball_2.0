package com.exercise.backend.Repository;

import com.exercise.backend.Entities.Debt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends CrudRepository<Debt, Long> {

}
