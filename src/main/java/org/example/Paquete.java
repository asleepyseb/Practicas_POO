package org.example;

public class Paquete {
    private String Destino;
    private double pesoKg;
    private EstrategiaEnvio estrategia;

    public Paquete(String destino, double pesoKg, EstrategiaEnvio estrategia) {
        if(pesoKg <=0) {
            System.out.println("El peso del paquete debe ser mayor a 0 kg.");
        }

        this.Destino = destino;
        this.pesoKg = pesoKg;
        this.estrategia = estrategia;
    }

    public double obtenerCostoEnvio() {
        return estrategia.calcularCosto(pesoKg);
    }

    public String getDestino() {
        return Destino;
    }
    public double getPesoKg() {
        return pesoKg;
    }
    public EstrategiaEnvio getEstrategia() {
        return estrategia;
    }
}
