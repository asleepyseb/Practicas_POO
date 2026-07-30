package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Registrar alumno (crear)");
            System.out.println("2. Imprimir todos los alumnos registrados");
            System.out.println("3. Actualizar un alumno por matrícula");
            System.out.println("4. Eliminar un alumno por matrícula");
            System.out.println("5. Imprimir cuántos hombres y mujeres hay");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try{
                // leer opcion como texto
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        new RegistrarAlumno().ejecutar(sc);
                        break;
                    case 2:
                        new ImprimirAlumno().ejecutar();
                        break;
                    case 3:
                        new ActualizarAlumno().ejecutar(sc);
                        break;
                    case 4:
                        new  EliminarAlumno().ejecutar(sc);
                        break;
                    case 5:
                        new alumnosPS().ejecutar();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida, intente de nuevo");
                }
            }catch(NumberFormatException e){
                System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 0);
        sc.close();
    }
}