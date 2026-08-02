package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorPaquetes gestor = new  GestorPaquetes();
        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Mostrar paquetes");
            System.out.println("3. Editar paquete (por ID)");
            System.out.println("4. Eliminar paquete (por ID)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n=== REGISTRAR PAQUETE ===");
                        System.out.print("Ingresa el destinatario del paquete: ");
                        String dest =  sc.nextLine();
                        System.out.print("Ingresa el peso del paquete (Kg): ");
                        double peso = Double.parseDouble(sc.nextLine());

                        EstrategiaEnvio estrategia = seleccionarEstrategia(sc);
                        Paquete nuevoPaquete = new Paquete(dest, peso, estrategia);
                        gestor.registrarPaquete(nuevoPaquete);
                        break;
                    case 2:
                        gestor.mostrarPaquetes();
                        break;
                    case 3:
                        gestor.mostrarPaquetes(); //se muestra la lista para identificar el ID a editar
                        System.out.println("\n=== EDITAR PAQUETE ===");
                        int idEditar = Integer.parseInt(sc.nextLine());

                        System.out.println("Nuevo destinatario del paquete: ");
                        String nuevoDest =   sc.nextLine();
                        System.out.println("Nuevo peso del paquete (Kg): ");
                        double nuevoPeso = Double.parseDouble(sc.nextLine());
                        EstrategiaEnvio nuevaEstrategia = seleccionarEstrategia(sc);
                        Paquete paqueteEditado = new Paquete(nuevoDest, nuevoPeso, nuevaEstrategia);
                        gestor.editarPaquete(idEditar, paqueteEditado);
                        break;
                    case 4:
                        System.out.println("\n=== ELIMINAR PAQUETE ===");
                        System.out.println("Ingresa el ID del paquete a eliminar: ");
                        int idEliminar = Integer.parseInt(sc.nextLine());
                        gestor.eliminarPaquete(idEliminar);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta nuevamente.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
            }catch (IllegalArgumentException e) {
                System.out.println("Error de datos: " + e.getMessage());
            }
        } while (opcion != 0);
        sc.close();
    }

    // metodo para que el menu de las estrategias no se repita en el main
    private static EstrategiaEnvio seleccionarEstrategia(Scanner sc) {
        System.out.println("Tipo de envio: 1. Estandar | 2. Express | 3. Internacional");
        System.out.println("Selecciona el tipo de envío (1, 2 o 3): ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 2) return new EnvioExpress();
        if (tipo == 3) return new EnvioInternacional();
        return new EnvioEstandar(); //opcion por defecto
    }
}
