package org.example;

public class EnvioExpress implements EstrategiaEnvio {
    @Override
    public double calcularCosto(double kg) {
        return (kg * 4.50) + 3.00;
    }
    @Override
    public String nombreEntrega() {
        return "Envío Express";
    }
}
