package org.example;

public class tarifaAuto implements tarifa {
    @Override
    public double calcular(int horas) {
        return horas * 2.00;
    }
}
