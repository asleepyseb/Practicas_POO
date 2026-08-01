package org.example;

public class tarifaCamion implements tarifa {
    @Override
    public double calcular(int horas) {
        return (horas * 4.00) + 5.00 ; // 5 USD de recargo
    }
}
