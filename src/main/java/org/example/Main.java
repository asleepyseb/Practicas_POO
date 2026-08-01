package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamiento plaza = new  Estacionamiento();
        int opcion = 0;

        do {
            System.out.println("\n=== ESTACIONAMIENTO ===");
            System.out.println("1. Registrar entrada de vehiculo");
            System.out.println("2. Registrar salida de vehiculo (cobrar)");
            System.out.println("3. Mostrar Reporte de vehiculos y recaudacion");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
             try{
                 opcion = Integer.parseInt(sc.nextLine());

                 switch (opcion) {
                     case 1:
                         System.out.println("\n=== REGISTRAR ENTRADA ===");
                         System.out.print("Ingrese placa del vehiculo: ");
                         String placa = sc.nextLine();
                         System.out.println("\n---- TIPOS DE VEHICULO ----");
                         System.out.println("1. Auto");
                         System.out.println("2. Moto");
                         System.out.println("3. Camion");
                         System.out.print("Seleccione el tipo de vehiculo: ");
                         int tipo= Integer.parseInt(sc.nextLine());

                         tarifa tarifaElegida;
                         if(tipo == 2) tarifaElegida = new tarifaMoto();
                         else if (tipo == 3) tarifaElegida = new tarifaCamion();
                         else tarifaElegida = new tarifaAuto();

                         vehiculo nuevoAuto = new vehiculoCliente(placa, 0, tarifaElegida);
                         plaza.registrarEntrada(nuevoAuto);
                         System.out.println("Vehiculo registrado correctamente.");
                         break;
                     case 2:
                         System.out.println("\n=== REGISTRAR SALIDA ===");
                         System.out.print("Ingrese placa del vehiculo: ");
                         String placaSalida = sc.nextLine();
                         System.out.println("Cuantas horas estuvo estacionado?");
                         int horas = Integer.parseInt(sc.nextLine());

                         plaza.registrarSalida(placaSalida, horas);
                         break;
                     case 3:
                         plaza.imprimirReporte();
                         break;
                     case 0:
                         System.out.println("Saliendo del programa...");
                         break;
                     default:
                         System.out.println("Opcion invalida. Intente nuevamente.");
                 }
             } catch (NumberFormatException e){
                 System.out.println("Error: Debe ingresar un numero entero. Intente nuevamente.");
                 opcion = -1;
             }
        }while  (opcion != 0);

        sc.close();
    }
}
