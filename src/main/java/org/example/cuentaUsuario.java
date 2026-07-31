package org.example;

public abstract class cuentaUsuario {
    private String correoElec;
    private int mesesActivo;
    // variable para calcular la tarifa
    private planSuscripcion plan;

    public cuentaUsuario(String correoElec, int mesesActivo, planSuscripcion plan) {
        this.correoElec = correoElec;
        this.mesesActivo = mesesActivo;
        this.plan = plan;
    }

    public String obtenerTotalPagar() {
        return plan.calcularCosto(this.mesesActivo);
    }

    public String getCorreoElec() {
        return correoElec;
    }

    public int getMesesActivo() {
        return mesesActivo;
    }

    public planSuscripcion getPlan() {
        return plan;
    }
}
