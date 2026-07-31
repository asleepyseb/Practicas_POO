package org.example;

public abstract class cuentaUsuario {
    private String correoElec;
    private int mesesActivo;
    // variable para calcular la tarifa
    private planSusc plan;

    public cuentaUsuario(String correoElec, int mesesActivo, planSusc plan) {
        this.correoElec = correoElec;
        this.mesesActivo = mesesActivo;
        this.plan = plan;
    }

    public double obtenerTotalPagar() {
        return plan.calcularCosto(this.mesesActivo);
    }

    public String getCorreoElec() {
        return correoElec;
    }

    public int getMesesActivo() {
        return mesesActivo;
    }

    public planSusc getPlan() {
        return plan;
    }
}
