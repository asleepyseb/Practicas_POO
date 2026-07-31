package org.example;

public class planPremium extends planEstandar {
    @Override
    public double calcularCosto(int meses) {
        return (meses * 14.00) + 3.00;
    }
}
