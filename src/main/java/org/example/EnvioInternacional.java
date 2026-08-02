package org.example;

public class EnvioInternacional implements EstrategiaEnvio {
    @Override
    public double calcularCosto(double kg) {
        return (kg * 6.00) + 10.00;
    }

    @Override
    public String nombreEntrega() {
        return "Envío Internacional";
    }
}
