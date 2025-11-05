package com.mycompany.gestorproductosxml;

import com.mycompany.gestorproductosxml.DOMManager;
import java.util.Scanner;

public class GestorProductosXML {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> DOMManager.mostrarProductos();
                case 2 -> DOMManager.agregarProducto();
                case 3 -> DOMManager.incrementarPreciosPorCategoria();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción incorrecta. Intente de nuevo.");
            }

            System.out.println();
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== GESTOR DE PRODUCTOS XML =====");
        System.out.println("1. Mostrar todos los productos (DOM)");
        System.out.println("2. Añadir un nuevo producto (DOM)");
        System.out.println("3. Incrementar precios de una categoría (DOM)");
        System.out.println("4. Generar informe de stock por categoría (SAX)");
        System.out.println("5. Mostrar precio medio global (SAX)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
}

