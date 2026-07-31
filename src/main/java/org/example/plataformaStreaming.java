package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class plataformaStreaming {
    private ArrayList<cuentaUsuario> cuentas;

    public plataformaStreaming() {
        this.cuentas = new ArrayList<>();
        cargarCuentas();
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
    // metodo para cargar las cuentas de la BD
    private void cargarCuentas(){
        String sql = "SELECT correo_electronico, meses_activo, tipo_plan FROM cuentas_streaming";

        try(Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String correoElec = rs.getString("correo_electronico");
                int meses = rs.getInt("meses_activo");
                String tipoPlan = rs.getString("tipo_plan");

                // Crear la cuenta según el tipo de plan
                planSusc planSeleccionado;
                if ("planBasico".equals(tipoPlan)) {
                    planSeleccionado = new planBasico();
                } else if ("planEstandar".equals(tipoPlan)) {
                    planSeleccionado = new planEstandar();
                } else if ("planPremium".equals(tipoPlan)) {
                    planSeleccionado = new planPremium();
                } else {
                    planSeleccionado = new planBasico();
                }
                // crea la cuenta para agregarla a la lista
                cuentaUsuario cuentaBD = new cuentaSuscrip(correoElec, meses, planSeleccionado);
                cuentas.add(cuentaBD);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar las cuentas: " + e.getMessage());
        }
    }
}
