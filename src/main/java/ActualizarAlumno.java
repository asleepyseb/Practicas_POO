import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class ActualizarAlumno {
    public void ejecutar(Scanner sc) {
        System.out.println("Ingrese la matricula del alumno: ");
        int matricula = sc.nextInt();
        System.out.println("Que desea actualizar del alumno?");
        System.out.println("1. Nombre del alumno");
        System.out.println("2. Edad del alumno");
        System.out.println("3. Sexo del alumno");
        System.out.println("4. Correo del alumno");
        System.out.println("5. Matricula del alumno");
        System.out.println("0. Cancelar");
        System.out.println("Ingrese la opcion: ");
        try{
            int  opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el neuevo nombre del alumno: ");
                    String nombre = sc.nextLine();
                    actualizarCampo("nombre", nombre, matricula, false);
                    break;
                case 2:
                    System.out.println("Ingrese la neueva edad del alumno: ");
                    int edad = Integer.parseInt(sc.nextLine());
                    actualizarCampo("edad" String.valueOf(edad), matricula, true);
                    break;
                case 3:
                    System.out.println("Ingrese el neuevo sexo del alumno: (H/M)": );
                    String sexo = sc.nextLine().toUpperCase();
                    actualizarCampo("sexo", sexo, matricula, false);
                    break;
                case 4:
                    System.out.println("Ingrese el neuevo correo del alumno: ");
                    String correo = sc.nextLine();
                    actualizarCampo("correo", correo, matricula, false);
                    break;
                case 5:
                    System.out.println("Ingrese la nueva matricula del alumno: ");
                    int nuevaMatricula = sc.nextLine();
                    actualizarCampo("matricula", nuevaMatricula, matricula, false);
                    break;
                case 0:
                    System.out.println("Accion cancelada.");
                    break;
                    default:
                        System.out.println("Opcion no valida");
            }
        }catch (NumberFormatException e){
            System.out.println("Error, ingresar numero valido ");
        }
    }
    // metodo auxiliar para no repetir el codigo JDBC
    private void actualizarCampo(String columna, String numeroValor, String matriculaAct, boolean esEntero){
        // consulta construida basandose en la matricula
        String sql = "UPDATE alumnos SET" + columna + " =? WHERE matricula=?";

        try(Connection conect = ConexionBD.getConnection();
            PreparedStatement pstmt = conect.prepareStatement(sql)){
            // si el valor a actualizar es numero se pasa a Int
            if (esEntero){
                pstmt.setInt(1, Integer.parseInt(numeroValor));
            }else {
                pstmt.setString(1,numeroValor);
            }
            // el segundo parametro siempre es la matricula para ubicar al alumno
            pstmt.setString(2, matriculaAct);

            int fila = pstmt.executeUpdate();
            if (fila > 0){
                System.out.println("El campo " + columna + " se ha actualizado correctamente");
            } else {
                System.out.println("No se encontro al alumno con la matricula");
            }
        }catch (SQLException e){
            System.out.println("Error al ejecutar la lista de alumnos" + e.getMessage());
        }
    }
}
