package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class plataformaStreaming {
    private ArrayList<cuentaUsuario> cuentas;

    public plataformaStreaming() {
        this.cuentas = new ArrayList<>();
    }

    public void registrarUsuario(cuentaUsuario cuenta){
        cuentas.add(cuenta); // guardar la cuenta
        String sql = "INSERT INTO cuentas_streaming (correo_electronico, meses_activo, tipo_plan) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cuenta.getCorreoElec());
            stmt.setInt(2, cuenta.getMesesActivo());
            stmt.setString(3, cuenta.getPlan().getClass().getSimpleName());
            stmt.executeUpdate();
            System.out.println("Usuario registrado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    public void imprimirReporte() {
        System.out.println("\n---- REPORTE ----");
        double totalRecaudado = 0.0;
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }
        for (cuentaUsuario cuenta : cuentas) {
            double pagar = cuenta.obtenerTotalPagar();
            totalRecaudado += pagar;
            String nombrePlan = cuenta.getPlan().getClass().getSimpleName();
            System.out.println("Usuario: " + cuenta.getCorreoElec() + "\n Plan: " + nombrePlan + "\n Meses activo: " + cuenta.getMesesActivo() + "\n Total a pagar: $" + String.format("%.2f", pagar));
        }
        System.out.println("------------------");
        System.out.println("Total recaudado: $" + String.format("%.2f", totalRecaudado));
    }
}
