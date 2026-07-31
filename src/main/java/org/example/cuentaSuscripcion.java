package org.example;

//clase para instanciar las cuentas
public class cuentaSuscripcion extends  cuentaUsuario {
    public cuentaSuscripcion(String correoElec, int mesesActivo, planSusc plan) {
        super(correoElec, mesesActivo, plan);
    }
}
