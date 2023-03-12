package com.exercise.backend;

import java.util.Comparator;

public class SortByBalance implements Comparator<Debt> {
    public int compare(Debt a, Debt b) {
        return a.balance - b.balance;
    }
}
