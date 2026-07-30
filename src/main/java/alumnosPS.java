import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class alumnosPS {
    public void ejecutar(){
        // consulta para agrupar los registros de columna sexo para contar cuantos hay de cada uno
        String sql = "SELECT sexo, COUNT(*) as total FROM alumnos GROUP BY sexo";

        try (Connection conect = ConexionBD.getConnection();
        Statement stmt = conect.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            System.out.println(" ---- ALUMNOS SEPARADOS POR SEXO ---- ");

            boolean hayDatos = false;
            while (rs.next()){
                hayDatos = true;
                String sexo = rs.getString("sexo");
                int total = rs.getInt("total");

                // convertir la letra (H/M) en algo mas legible
                String tag = sexo.equals("H") ? "Hombres" : (sexo.equals("M") ? "Mujeres" : "Otro");
                System.out.println(tag + ": " + total);
            }
            
            if (!hayDatos) {
                System.out.println("No hay alumnos registrados");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
