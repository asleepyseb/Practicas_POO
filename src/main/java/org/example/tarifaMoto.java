package org.example;

public class tarifaMoto implements tarifa {
    @Override
    public double calcular(int horas) {
        return horas * 1.00;
    }
}
