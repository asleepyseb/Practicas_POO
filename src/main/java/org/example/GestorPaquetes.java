package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//CRUD creado como gestor paquetes para optimizar el codigo
public class GestorPaquetes {

    // registrar paquete
    public void registrarPaquete(Paquete paquete) {
        String sql = "INSERT INTO registro_paquetes (destinatario, peso_kg, tipo_envio, costo_total) VALUES (?, ?, ?, ?)";
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paquete.getDestino());
            stmt.setDouble(2, paquete.getPesoKg());
            stmt.setString(3, paquete.getEstrategia().nombreEntrega());
            stmt.setDouble(4, paquete.obtenerCostoEnvio());
            stmt.executeUpdate();
            System.out.println("Paquete registrado exitosamente | Destino: " + paquete.getDestino());
        } catch (SQLException e) {
            System.out.println("Error al registrar el paquete: " + e.getMessage());
        }
    }

    // mostrar registros
    public void mostrarPaquetes() {
        String sql = "SELECT id, destinatario, peso_kg, tipo_envio, costo_total FROM registro_paquetes";
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== REGISTRO PAQUETES ===");
            boolean hayDatos = false;
            while(rs.next()) {
                hayDatos = true;
                System.out.println("ID: " + rs.getInt("id") +
                        "\nDestino: " + rs.getString("destinatario") +
                        "\nPeso (kg): " + rs.getDouble("peso_kg") + "kg" +
                        "\nTipo de envío: " + rs.getString("tipo_envio") +
                        "\nCosto total: $" + rs.getDouble("costo_total"));
            }
            if (!hayDatos) {
                System.out.println("No hay paquetes registrados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los paquetes: " + e.getMessage());
        }
    }

    // editar registro por ID
    public void editarPaquete(int id, Paquete nuevosDatos) {
        String sql = "UPDATE registro_paquetes SET destinatario = ?, tipo_envio = ?, costo_total = ? WHERE id = ?";
        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nuevosDatos.getDestino());
            stmt.setDouble(2, nuevosDatos.getPesoKg());
            stmt.setString(3, nuevosDatos.getEstrategia().nombreEntrega());
            stmt.setDouble(4, nuevosDatos.obtenerCostoEnvio());
            stmt.setInt(5, id);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Paquete con ID " + id + " actualizado exitosamente.");
            } else {
                System.out.println("No se encontró un paquete con ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el paquete: " + e.getMessage());
        }
    }

    // eliminar por id
    public void eliminarPaquete(int id) {
        String sql = "DELETE FROM registro_paquetes WHERE id = ?";
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Paquete con ID " + id + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un paquete con ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el paquete: " + e.getMessage());
        }
    }
}
