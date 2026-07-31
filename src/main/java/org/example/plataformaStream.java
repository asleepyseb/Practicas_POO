package org.example;

import java.util.ArrayList;

public class plataformaStream {
    private ArrayList<cuentaUsuario> cuentas;

    public plataformaStream() {
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(cuentaUsuario cuenta) {
        this.cuentas.add(cuenta);
        System.out.println("Usuario agregado con exito: " + cuenta.getCorreoElec());
    }

    public void imprimirReporte(){
        System.out.println("\n ---- REPORTE ----");
        double totalRecaudado = 0.0;
        if(cuentas.isEmpty()){
            System.out.println("No hay cuentas registradas.");
            return;
        }
        for(cuentaUsuario cuenta: cuentas){
            double pagar = cuenta.obtenerTotalPagar();
            totalRecaudado += pagar;
            // encontrar el nombre del plan
            String nombrePlan = cuenta.getPlan().getClass().getSimpleName();
            System.out.println("Usuario: " + cuenta.getCorreoElec() + "\n Plan: " + nombrePlan + "\n Meses activo: " + cuenta.getMesesActivo() + "\n Total a pagar: $" + String.format("%.2f", pagar));
        }
        System.out.println("------------------");
        System.out.println("Total recaudado: $" + String.format("%.2f", totalRecaudado));
    }
}
