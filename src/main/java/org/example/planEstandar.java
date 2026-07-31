package org.example;

public class planEstandar implements planSusc {
    @Override
    public double calcularCosto(int meses) {
        return meses * 9.00;
    }
}
