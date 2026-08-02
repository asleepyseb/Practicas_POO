package org.example;

public class EnvioEstandar implements EstrategiaEnvio {
    @Override
    public double calcularCosto(double kg) {
        return kg * 2.00;
    }
    @Override
    public String nombreEntrega() {
        return "Envío Estandar";
    }
}
