package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImprimirAlumno {
    public void ejecutar(){
        String sql = "SELECT * FROM alumnos";
        try (Connection conect = ConexionBD.getConnection();
             Statement stmt = conect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            System.out.println("--- LISTA ALUMNOS ---");
            while (rs.next()){
                System.out.printf("Matricula: %s | Nombre: %s | Edad: %d | Sexo: %s | Correo: %n ",
                        rs.getString("matricula"), rs.getString("nombre"), rs.getInt("edad"), rs.getString("Sexo"), rs.getString("correo"));
            }
        }catch (SQLException e){
            System.out.println("Error al ejecutar la lista de alumnos" + e.getMessage());
        }
    }
}
