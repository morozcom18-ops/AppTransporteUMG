package umg.UI;

import umg.DAO.*;
import umg.modelo.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AppTransporteUmg {

    private static Scanner sc = new Scanner(System.in);
    private static CamioneroDAO camioneroDAO = new CamioneroDAO();
    private static CamionDAO camionDAO = new CamionDAO();
    private static PaqueteDAO paqueteDAO = new PaqueteDAO();
    private static CamioneroCamionDAO camioneroCamionDAO = new CamioneroCamionDAO();

    public static void main(String[] args) {
        int opcion = -1;
        do {
            System.out.println("\n========== SISTEMA DB_PAQUETERIA ==========");
            System.out.println("1. Gestión de Camioneros");
            System.out.println("2. Gestión de Camiones");
            System.out.println("3. Gestión de Paquetes");
            System.out.println("4. Asignación Camionero - Camión");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1 -> menuCamionero();
                    case 2 -> menuCamion();
                    case 3 -> menuPaquete();
                    case 4 -> menuCamioneroCamion();
                    case 0 -> System.out.println("¡Gracias por utilizar el sistema!");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico entero.");
            }
        } while (opcion != 0);
    }

    private static void menuCamionero() {
        System.out.println("\n--- CRUD CAMIONEROS ---");
        System.out.println("1. Crear | 2. Listar | 3. Actualizar | 4. Eliminar");
        System.out.print("Opción: ");
        String op = sc.nextLine();
        try {
            switch (op) {
                case "1" -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine().trim();
                    if (dni.isEmpty()) { System.out.println("El DNI es obligatorio."); return; }
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) { System.out.println("El nombre es obligatorio."); return; }
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Población: ");
                    String pob = sc.nextLine();
                    System.out.print("Salario: ");
                    double salario = Double.parseDouble(sc.nextLine());
                    if (salario <= 0) { System.out.println("El salario debe ser mayor a 0."); return; }

                    camioneroDAO.insertar(new Camionero(dni, nombre, tel, dir, pob, salario));
                    System.out.println("¡Camionero registrado exitosamente!");
                }
                case "2" -> {
                    List<Camionero> lista = camioneroDAO.listar();
                    lista.forEach(c -> System.out.println("DNI: " + c.getDni() + " | Nombre: " + c.getNombre() + " | Tel: " + c.getTelefono() + " | Salario: Q" + c.getSalario()));
                }
                case "3" -> {
                    System.out.print("DNI del camionero a modificar: ");
                    String dni = sc.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Nuevo Teléfono: ");
                    String tel = sc.nextLine();
                    System.out.print("Nueva Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Nueva Población: ");
                    String pob = sc.nextLine();
                    System.out.print("Nuevo Salario: ");
                    double sal = Double.parseDouble(sc.nextLine());

                    camioneroDAO.actualizar(new Camionero(dni, nom, tel, dir, pob, sal));
                    System.out.println("¡Camionero actualizado correctamente!");
                }
                case "4" -> {
                    System.out.print("DNI del camionero a eliminar: ");
                    String dni = sc.nextLine();
                    camioneroDAO.eliminar(dni);
                    System.out.println("¡Camionero eliminado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al procesar: " + e.getMessage());
        }
    }

    private static void menuCamion() {
        System.out.println("\n--- CRUD CAMIONES ---");
        System.out.println("1. Crear | 2. Listar | 3. Actualizar | 4. Eliminar");
        System.out.print("Opción: ");
        String op = sc.nextLine();
        try {
            switch (op) {
                case "1" -> {
                    System.out.print("Matrícula: ");
                    String mat = sc.nextLine().trim();
                    if (mat.isEmpty()) { System.out.println("La matrícula no puede estar vacía."); return; }
                    System.out.print("Modelo: ");
                    String mod = sc.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Potencia (HP): ");
                    int pot = Integer.parseInt(sc.nextLine());

                    camionDAO.insertar(new Camion(mat, mod, tipo, pot));
                    System.out.println("¡Camión guardado exitosamente!");
                }
                case "2" -> {
                    List<Camion> lista = camionDAO.listar();
                    lista.forEach(c -> System.out.println("Matrícula: " + c.getMatricula() + " | Modelo: " + c.getModelo() + " | Tipo: " + c.getTipo() + " | Potencia: " + c.getPotencia() + " HP"));
                }
                case "3" -> {
                    System.out.print("Matrícula a modificar: ");
                    String mat = sc.nextLine();
                    System.out.print("Nuevo Modelo: ");
                    String mod = sc.nextLine();
                    System.out.print("Nuevo Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Nueva Potencia: ");
                    int pot = Integer.parseInt(sc.nextLine());

                    camionDAO.actualizar(new Camion(mat, mod, tipo, pot));
                    System.out.println("¡Camión actualizado!");
                }
                case "4" -> {
                    System.out.print("Matrícula a eliminar: ");
                    String mat = sc.nextLine();
                    camionDAO.eliminar(mat);
                    System.out.println("¡Camión eliminado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuPaquete() {
        System.out.println("\n--- CRUD PAQUETES ---");
        System.out.println("1. Crear | 2. Listar | 3. Actualizar | 4. Eliminar");
        System.out.print("Opción: ");
        String op = sc.nextLine();
        try {
            switch (op) {
                case "1" -> {
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    System.out.print("Destinatario: ");
                    String dest = sc.nextLine();
                    System.out.print("Dirección Destinatario: ");
                    String dir = sc.nextLine();
                    System.out.print("DNI Camionero (Presione Enter si aún no tiene camionero asignado): ");
                    String dni = sc.nextLine().trim();

                    paqueteDAO.insertar(new Paquete(0, desc, dest, dir, dni));
                    System.out.println("¡Paquete creado exitosamente!");
                }
                case "2" -> {
                    List<Paquete> lista = paqueteDAO.listar();
                    lista.forEach(p -> System.out.println("Código: " + p.getCodigo() + " | Desc: " + p.getDescripcion() + " | Destino: " + p.getDestinatario() + " | DNI Camionero: " + p.getDniCamionero()));
                }
                case "3" -> {
                    System.out.print("Código del paquete a actualizar: ");
                    int cod = Integer.parseInt(sc.nextLine());
                    System.out.print("Nueva Descripción: ");
                    String desc = sc.nextLine();
                    System.out.print("Nuevo Destinatario: ");
                    String dest = sc.nextLine();
                    System.out.print("Nueva Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Nuevo DNI Camionero: ");
                    String dni = sc.nextLine().trim();

                    paqueteDAO.actualizar(new Paquete(cod, desc, dest, dir, dni));
                    System.out.println("¡Paquete actualizado!");
                }
                case "4" -> {
                    System.out.print("Código del paquete a eliminar: ");
                    int cod = Integer.parseInt(sc.nextLine());
                    paqueteDAO.eliminar(cod);
                    System.out.println("¡Paquete eliminado!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuCamioneroCamion() {
        System.out.println("\n--- ASIGNACIÓN CAMIONERO A CAMIÓN ---");
        System.out.println("1. Asignar | 2. Listar | 3. Actualizar | 4. Eliminar");
        System.out.print("Opción: ");
        String op = sc.nextLine();
        try {
            switch (op) {
                case "1" -> {
                    System.out.print("DNI Camionero: ");
                    String dni = sc.nextLine();
                    System.out.print("Matrícula Camión: ");
                    String mat = sc.nextLine();
                    System.out.print("Fecha (Formato YYYY-MM-DD): ");
                    Date fecha = Date.valueOf(sc.nextLine().trim());

                    camioneroCamionDAO.insertar(new CamioneroCamion(0, dni, mat, fecha));
                    System.out.println("¡Asignación guardada con éxito!");
                }
                case "2" -> {
                    List<CamioneroCamion> lista = camioneroCamionDAO.listar();
                    lista.forEach(cc -> System.out.println("ID: " + cc.getId() + " | DNI Camionero: " + cc.getDniCamionero() + " | Matrícula: " + cc.getMatriculaCamion() + " | Fecha: " + cc.getFecha()));
                }
                case "3" -> {
                    System.out.print("ID del registro a actualizar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Nuevo DNI Camionero: ");
                    String dni = sc.nextLine();
                    System.out.print("Nueva Matrícula Camión: ");
                    String mat = sc.nextLine();
                    System.out.print("Nueva Fecha (YYYY-MM-DD): ");
                    Date fecha = Date.valueOf(sc.nextLine().trim());

                    camioneroCamionDAO.actualizar(new CamioneroCamion(id, dni, mat, fecha));
                    System.out.println("¡Asignación actualizada!");
                }
                case "4" -> {
                    System.out.print("ID a eliminar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    camioneroCamionDAO.eliminar(id);
                    System.out.println("¡Asignación eliminada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
