package org.example;

public abstract class vehiculo {
    private String placa;
    private int horasEst;
    private tarifa tarifa;

    public vehiculo(String placa, int horasEst, tarifa tarifa) {
        this.placa = placa;
        this.horasEst = horasEst;
        this.tarifa = tarifa;
    }

    public double calcularCostoEstacionamiento(){
        return tarifa.calcular(this.horasEst);
    }

    public String getPlaca() {
        return placa;
    }
    public int  getHorasEst() {
        return horasEst;
    }
    public void setHorasEst(int horasEst) {
        this.horasEst = horasEst;
    }
    public tarifa getTarifa() {
        return tarifa;
    }
}
