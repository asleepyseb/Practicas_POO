package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        plataformaStreaming plataforma = new plataformaStreaming();
        int opcion = 0;

        do {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Imprimir reporte general");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try{
                opcion = Integer.parseInt(sc.nextLine());

                switch(opcion){
                    case 1:
                        System.out.println("Ingrese correo electrónico:");
                        String correoElec = sc.nextLine();
                        System.out.println("Ingrese los meses a contratar:");
                        int meses = Integer.parseInt(sc.nextLine());

                        System.out.println("\nSeleccione el plan: \n1. Básico ($5 USD p/mes) \n2. Estándar ($9 USD p/mes) \n3. Premium ($14 USD p/mes + $3 de recargo unico)");
                        System.out.print("Opción: ");
                        int tipo = Integer.parseInt(sc.nextLine());

                        planSusc planSeleccionado; //variable de la interf
                        if(tipo == 1){
                            planSeleccionado = new planBasico();
                        } else if(tipo == 2){
                            planSeleccionado = new planEstandar();
                        } else if(tipo == 3){
                            planSeleccionado = new planPremium();
                        } else{
                            System.out.println("Opción inválida. Se asignará el plan básico por defecto.");
                            planSeleccionado = new planBasico();
                        }

                        //creacion de la cuenta
                        cuentaUsuario nuevaCuenta = new cuentaSuscrip(correoElec, meses, planSeleccionado);
                        //registrar la cuenta
                        plataforma.registrarUsuario(nuevaCuenta);
                        break;
                    case 2:
                        plataforma.imprimirReporte();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e){
                System.out.println("Error: Por favor ingrese un número correcto.");
                opcion =-1;
            }
        }while(opcion !=0);
        sc.close();
    }
}
