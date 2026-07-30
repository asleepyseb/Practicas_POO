package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegistrarAlumno {
 public void ejecutar(Scanner sc){
     System.out.println("Ingrese la matricula del alumno: ");
     String matricula = sc.nextLine();
     System.out.println("Ingrese el nombre completo del alumno: ");
     String nombre = sc.nextLine();
     System.out.println("Ingrese la edad del alumno: ");
     int edad = Integer.parseInt(sc.nextLine());
     System.out.println("Ingrese el sexo del alumno (H/M): ");
     String sexo = sc.nextLine().toUpperCase();
     System.out.println("Ingrese el correo del alumno: ");
     String correo = sc.nextLine();

     String sql = "INSERT INTO alumnos (matricula, nombre, edad, sexo, correo) VALUES (?, ?, ?, ?, ?)";

     try (Connection conect = ConexionBD.getConnection();
          PreparedStatement pstmt = conect.prepareStatement(sql)){

         pstmt.setString(1, matricula);
         pstmt.setString(2, nombre);
         pstmt.setInt(3, edad);
         pstmt.setString(4, sexo);
         pstmt.setString(5, correo);
         pstmt.executeUpdate();
         System.out.println("Alumno registrado correctamente");
     }
     catch (SQLException e){
         System.out.println("Error al registrar al alumno" + e.getMessage());
     }
 }
}
