package org.example;

public class planBasico implements planSusc {
    @Override
    public double calcularCosto(int meses) {
        return meses * 5.00;
    }
}
