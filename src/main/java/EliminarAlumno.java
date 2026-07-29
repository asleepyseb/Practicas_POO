import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EliminarAlumno {
    public void ejecutar(Scanner sc){
        System.out.println("Ingrese la matricula del alumno a eliminar: ");
        String matricula = sc.nextLine();

        String sql = "DELETE FROM alumnos WHERE matricula = ?";
        try(Connection conect = ConexionBD.getConnection();
        PreparedStatement pstmt = conect.prepareStatement(sql)){
            pstmt.setString(1, matricula);

            int fila = pstmt.executeUpdate();
            if(fila > 0){
                System.out.println("Alumno eliminado");
            } else{
                System.out.println("Alumno no encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
