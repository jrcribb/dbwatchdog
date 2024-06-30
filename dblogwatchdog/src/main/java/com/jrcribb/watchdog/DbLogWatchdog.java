package com.jrcribb.watchdog;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DbLogWatchdog {

    private static final Scanner scanner = new Scanner(System.in);
    private static final OrigenDAO origenDAO = new OrigenDAO();

    public static void main(String[] args) {
        boolean running = true;
        printMenu();
        while (running) {
            System.out.print("\nElige una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.print("");
            printMenu();
            switch (choice) {
                case 1:
                    createOrigen();
                    break;
                case 2:
                    readOrigen();
                    break;
                case 3:
                    updateOrigen();
                    break;
                case 4:
                    deleteOrigen();
                    break;
                case 5:
                    readAllOrigenes();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n-- Mantenimiento de Origenes de Logs");
        System.out.println("1. Crear Origen");
        System.out.println("2. Leer Origen");
        System.out.println("3. Actualizar Origen");
        System.out.println("4. Eliminar Origen");
        System.out.println("5. Leer todos los Origenes");
        System.out.println("6. Salir");
        System.out.println("");
    }

    private static void createOrigen() {
        System.out.printf("> Crear Origen\n\n");
        System.out.printf("%-30s%s","Origen",": ");
        String origen = scanner.nextLine();
        System.out.printf("%-30s%s","Ubicación",": ");
        String ubicacion = scanner.nextLine();

        Origen newOrigen = new Origen(0, origen, ubicacion);

        try {
            origenDAO.create(newOrigen);
            System.out.println("Origen creado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear el origen: " + e.getMessage());
        }
    }

    private static void readOrigen() {
        System.out.printf("> Leer Origen\n\n");
        System.out.printf("%-30s%s","ID del origen",": ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("ID\tCODIGO\tUBICACION\n");

        try {
            Origen origen = origenDAO.read(id);
            if (origen != null) {
                System.out.printf("%d\t%s\t%s\n", origen.getId(), origen.getOrigen(), origen.getUbicacion());
            } else {
                System.out.println("Origen no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al leer el origen: " + e.getMessage());
        }
    }

    private static void updateOrigen() {
        System.out.printf("> Actualizar Origen\n\n");
        System.out.printf("%-30s%s","ID del origen",": ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Origen origen = origenDAO.read(id);
            if (origen != null) {
                System.out.printf("%-30s%s","Origen",": ");
                String newOrigen = scanner.nextLine();
                System.out.printf("%-30s%s","Ubicación",": ");
                String newUbicacion = scanner.nextLine();

                origen.setOrigen(newOrigen);
                origen.setUbicacion(newUbicacion);

                origenDAO.update(origen);
                System.out.println("Origen actualizado exitosamente.");
            } else {
                System.out.println("Origen no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el origen: " + e.getMessage());
        }
    }

    private static void deleteOrigen() {
        System.out.printf("> Eliminar Origen\n\n");
        System.out.printf("%-30s%s","ID del origen",": ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            origenDAO.delete(id);
            System.out.println("Origen eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el origen: " + e.getMessage());
        }
    }

    private static void readAllOrigenes() {
        System.out.printf("> Leer todos los Orígenes\n\n");
        try {
            List<Origen> origenes = origenDAO.readAll();
            System.out.printf("ID\tCODIGO\tUBICACION\n");
            origenes.forEach(origen -> System.out.printf("%d\t%s\t%s\n", origen.getId(), origen.getOrigen(), origen.getUbicacion()));
        } catch (SQLException e) {
            System.out.println("Error al leer los origenes: " + e.getMessage());
        }
    }
}
