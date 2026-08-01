package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estacionamiento {
    private ArrayList<vehiculo> vehiculos;

    public Estacionamiento() {
        this.vehiculos = new ArrayList<>();
        cargarVehiculosBD(); //metodo para cargar los datos de la BD
    }

    private void cargarVehiculosBD() {
        String sql = "SELECT placa, tipo_vehiculo FROM registro_estacionamiento WHERE estado= 'DENTRO'";
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String placa = rs.getString("placa");
                String tipo = rs.getString("tipo_vehiculo");

                //asignar las tarifas
                tarifa tarifaAsignada = new tarifaAuto(); //tarifa por defecto (auto)
                if(tipo.equals("tarifaMoto")) tarifaAsignada = new tarifaMoto();
                if(tipo.equals("tarifaCamion")) tarifaAsignada = new tarifaCamion();

                vehiculos.add(new vehiculoCliente(placa, 0, tarifaAsignada)); // 0 horas por defecto
            }
        }catch (SQLException e){
            System.out.println("Error al cargar los datos en el registro " + e.getMessage());
        }
    }

    public void registrarEntrada(vehiculo v ){
        vehiculos.add(v);
        String tipo =  v.getTarifa().getClass().getSimpleName();
        String tablaEspecifica = "";

        //buscar las tablas segun la tarifa
        if (tipo.equals("tarifaAuto")) tablaEspecifica = "autos_activos";
        else if (tipo.equals("tarifaMoto")) tablaEspecifica = "motos_activas";
        else if (tipo.equals("tarifaCamion")) tablaEspecifica = "camiones_activos";

        String tablaGeneral = "INSERT INTO registro_estacionamiento (placa, tipo_vehiculo, estado) VALUES (?, ?, 'DENTRO')";
        String sqlEspecifico = "INSERT INTO " + tablaEspecifica + " (placa) VALUES (?)";

        try(Connection con = conexionBD.getConnection();
        PreparedStatement stmtGen = con.prepareStatement(tablaGeneral);
        PreparedStatement stmtEsp = con.prepareStatement(sqlEspecifico)){
            //tabla general
            stmtGen.setString(1, v.getPlaca());
            stmtGen.setString(2, tipo);
            stmtGen.executeUpdate();
            //tabla especifica
            stmtEsp.setString(1, v.getPlaca());
            stmtEsp.executeUpdate();

            System.out.println("Vehículo con placa " + v.getPlaca() + " registrado correctamente.");

        } catch (SQLException e){
            System.out.println("Error al registrar el vehiculo: " +  e.getMessage());
        }
    }

    public void registrarSalida(String placaBuscada, int horas){
        vehiculo vehiculoEncontrado = null;
        for (vehiculo v : vehiculos) {
            if ( v.getPlaca().equalsIgnoreCase(placaBuscada)) {
                vehiculoEncontrado = v;
                break;
            }
        }
        if (vehiculoEncontrado == null) {
            System.out.println("No se encontró un vehículo con la placa proporcionada.");
            return;
        }

        //actualizar horas y cobrar
        vehiculoEncontrado.setHorasEst((horas));
        double totalCobrar = vehiculoEncontrado.calcularCostoEstacionamiento();
        String tipo = vehiculoEncontrado.getTarifa().getClass().getSimpleName();

        String tablaEspecifica = "";
        if(tipo.equals("tarifaAuto")) tablaEspecifica = "autos_activos";
        else if(tipo.equals("tarifaMoto")) tablaEspecifica = "motos_activas";
        else if(tipo.equals("tarifaCamion")) tablaEspecifica = "camiones_activos";

        //actualizar a cobrado
        String sqlUpdate = "UPDATE registro_estacionamiento  SET estado = 'COBRADO', horas_totales = ?, total_pagado = ? WHERE placa = ? AND estado = 'DENTRO'";
        // liberar espacio
        String sqlDelete = "DELETE FROM" +  tablaEspecifica + " WHERE placa = ?";

        try (Connection con = conexionBD.getConnection();
        PreparedStatement stmtUp = con.prepareStatement(sqlUpdate);
        PreparedStatement stmtDel = con.prepareStatement(sqlDelete)){
            //actualizar el registro
            stmtUp.setInt(1, horas);
            stmtUp.setDouble(2, totalCobrar);
            stmtUp.setString(3, vehiculoEncontrado.getPlaca()   );
            stmtUp.executeUpdate();

            // liberar espacio
            stmtDel.setString(1, vehiculoEncontrado.getPlaca());
            stmtDel.executeUpdate();

            vehiculos.remove(vehiculoEncontrado); //aqui se saca de la memoria el vehiculo que se encontro

            //reporte
            System.out.println("\n=== TICKET ===");
            System.out.println("Placa: " + vehiculoEncontrado.getPlaca());
            System.out.println("Horas estacionadas: " + horas);
            System.out.println("Total a pagar: $" + totalCobrar);
            System.out.println("--------------------------------");

        } catch (SQLException e) {
            System.out.println("Error al registrar la salida del vehículo: " + e.getMessage());
        }
    }

    //reporte del estacionamiento
    public void imprimirReporte(){
        System.out.println("\n=== REPORTE DE VEHÍCULOS DENTRO DEL ESTACIONAMIENTO ===");
        if(vehiculos.isEmpty()){
            System.out.println("no hay vehiculos dentro del estacionamiento");
        } else{
            for (vehiculo v : vehiculos) {
                System.out.println("Placa: " + v.getPlaca() + ", Tipo: " + v.getTarifa().getClass().getSimpleName());
            }
        }

        //consulta del dinero total recaudado
        String sql = "SELECT SUM(total_pagado) AS recaudacion FROM registro_estacionamiento";
        try ( Connection con = conexionBD.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                double total = rs.getDouble("recaudacion");
                System.out.println("\nTotal recaudado: $" + String.format("%.2f", total));
            }
        }catch (SQLException e){
            System.out.println("Error al obtener la salida del estacionamiento: " + e.getMessage());
        }
    }
}
