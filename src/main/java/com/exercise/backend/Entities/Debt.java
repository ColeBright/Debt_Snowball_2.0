 package com.exercise.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "debt")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "debt_id")
    public int id;
    @Column(name = "debt_balance", nullable = false)
    public int balance;
    @Column(name = "debt_payment", nullable = false)
    public  int payment;

    public Debt(){

    }

    public Debt(int balance, int payment) {
        this.balance = balance;
        this.payment = payment;
    }
}
