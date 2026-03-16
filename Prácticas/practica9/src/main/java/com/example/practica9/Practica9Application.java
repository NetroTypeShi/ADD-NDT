package com.example.practica9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class Practica9Application {

    public static void main(String[] args) {
        System.setProperty("spring.data.mongodb.uri", 
            "mongodb+srv://andresmenduina_db_user:h4ncITppaK0qy482@accesoadatos.1q0y1co.mongodb.net/AccesoADatosDB?retryWrites=true&w=majority&appName=AccesoADatos");
        
        SpringApplication.run(Practica9Application.class, args);
    }

    @Bean
    public CommandLineRunner run(TransactionRepository transactionRepository) {
        return args -> {
            try {
                // Cargar datos de prueba solo si la BD esta vacia
                if (transactionRepository.count() == 0) {
                    System.out.println("\n[INFO] Cargando datos de prueba iniciales...\n");
                    cargarDatosPrueba(transactionRepository);
                }
                
                // Iniciar menu interactivo
                menuInteractivo(transactionRepository);
                
            } catch (Exception e) {
                System.out.println("\nError de conexion a MongoDB:");
                System.out.println("Asegurate de que:");
                System.out.println("- Tienes internet");
                System.out.println("- La URI de conexion es correcta");
                System.out.println("- Tu IP esta en la whitelist de MongoDB Atlas");
                e.printStackTrace();
            }
        };
    }

    private static void cargarDatosPrueba(TransactionRepository repo) {
        // Fechas para las pruebas
        Date fecha1 = convertToDate(LocalDate.now().minusDays(45));
        Date fecha2 = convertToDate(LocalDate.now().minusDays(30));
        Date fecha3 = convertToDate(LocalDate.now().minusDays(15));
        Date fecha4 = convertToDate(LocalDate.now().minusDays(5));
        Date fecha5 = convertToDate(LocalDate.now());
        
        // Crear transacciones de prueba
        Transaction t1 = new Transaction("1", "Salario Mensual", 2500.00, true, fecha2);
        Transaction t2 = new Transaction("2", "Compra en Supermercado", 85.50, false, fecha3);
        Transaction t3 = new Transaction("3", "Bonus del trabajo", 500.00, true, fecha4);
        Transaction t4 = new Transaction("4", "Compra de ropa", 150.75, false, fecha5);
        Transaction t5 = new Transaction("5", "Freelance proyecto", 350.00, true, fecha1);
        Transaction t6 = new Transaction("6", "Pago de servicios", 120.00, false, fecha4);
        
        repo.save(t1);
        repo.save(t2);
        repo.save(t3);
        repo.save(t4);
        repo.save(t5);
        repo.save(t6);
        
        System.out.println("Se han cargado 6 transacciones de prueba.\n");
    }

    private static void menuInteractivo(TransactionRepository transactionRepository) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            System.out.print("\nSelecciona una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        agregarTransaccion(scanner, transactionRepository);
                        break;
                    case 2:
                        verIngresos(transactionRepository);
                        break;
                    case 3:
                        verGastos(transactionRepository);
                        break;
                    case 4:
                        verTodasLasTransacciones(transactionRepository);
                        break;
                    case 5:
                        buscarPorDescripcion(scanner, transactionRepository);
                        break;
                    case 6:
                        verResumen(transactionRepository);
                        break;
                    case 7:
                        eliminarTransaccion(scanner, transactionRepository);
                        break;
                    case 8:
                        salir = true;
                        System.out.println("\nHasta luego!\n");
                        break;
                    default:
                        System.out.println("\nOpcion no valida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingresa un numero valido.");
            }
            
            if (!salir) {
                System.out.print("\nPresiona Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       GESTOR DE GASTOS E INGRESOS");
        System.out.println("=".repeat(50));
        System.out.println("1. Agregar nueva transaccion");
        System.out.println("2. Ver todos los ingresos");
        System.out.println("3. Ver todos los gastos");
        System.out.println("4. Ver todas las transacciones");
        System.out.println("5. Buscar por descripcion");
        System.out.println("6. Ver resumen (ingresos vs gastos)");
        System.out.println("7. Eliminar transaccion");
        System.out.println("8. Salir");
        System.out.println("=".repeat(50));
    }

    private static void agregarTransaccion(Scanner scanner, TransactionRepository repo) {
        System.out.println("\n--- Agregar Nueva Transaccion ---");
        
        try {
            System.out.print("Descripcion: ");
            String descripcion = scanner.nextLine();
            
            System.out.print("Cantidad: ");
            double cantidad = Double.parseDouble(scanner.nextLine());
            
            if (cantidad <= 0) {
                System.out.println("Error: La cantidad debe ser mayor a 0.");
                return;
            }
            
            System.out.print("Es un ingreso? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();
            boolean esIngreso = respuesta.equals("s");
            
            Transaction t = new Transaction(
                UUID.randomUUID().toString(),
                descripcion,
                cantidad,
                esIngreso,
                new Date()
            );
            
            repo.save(t);
            System.out.println("\nTransaccion agregada correctamente!");
            System.out.println("ID: " + t.getId());
            System.out.println("Tipo: " + (esIngreso ? "INGRESO" : "GASTO"));
            System.out.println("Cantidad: " + String.format("%.2f", cantidad) + " EUR");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero valido para la cantidad.");
        }
    }

    private static void verIngresos(TransactionRepository repo) {
        System.out.println("\n--- INGRESOS ---");
        List<Transaction> ingresos = repo.findByIncomeIsTrue();
        
        if (ingresos.isEmpty()) {
            System.out.println("No hay ingresos registrados.");
            return;
        }
        
        double total = 0;
        for (Transaction t : ingresos) {
            System.out.println("  - " + t.getDescription() + " | " + 
                String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
            total += t.getQuantity();
        }
        System.out.println("  " + "-".repeat(40));
        System.out.println("  TOTAL INGRESOS: " + String.format("%.2f", total) + " EUR");
    }

    private static void verGastos(TransactionRepository repo) {
        System.out.println("\n--- GASTOS ---");
        List<Transaction> gastos = repo.findByIncomeIsFalse();
        
        if (gastos.isEmpty()) {
            System.out.println("No hay gastos registrados.");
            return;
        }
        
        double total = 0;
        for (Transaction t : gastos) {
            System.out.println("  - " + t.getDescription() + " | " + 
                String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
            total += t.getQuantity();
        }
        System.out.println("  " + "-".repeat(40));
        System.out.println("  TOTAL GASTOS: " + String.format("%.2f", total) + " EUR");
    }

    private static void verTodasLasTransacciones(TransactionRepository repo) {
        System.out.println("\n--- TODAS LAS TRANSACCIONES ---");
        List<Transaction> todas = repo.findAll();
        
        if (todas.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
            return;
        }
        
        for (Transaction t : todas) {
            String tipo = t.isIncome() ? "[INGRESO]" : "[GASTO]";
            System.out.println("  - " + tipo + " " + t.getDescription() + 
                " | " + String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
        }
        System.out.println("\n  Total de transacciones: " + todas.size());
    }

    private static void buscarPorDescripcion(Scanner scanner, TransactionRepository repo) {
        System.out.println("\n--- BUSCAR POR DESCRIPCION ---");
        System.out.print("Palabra a buscar: ");
        String palabra = scanner.nextLine();
        
        List<Transaction> resultados = repo.findByDescriptionContaining(palabra);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron transacciones con '" + palabra + "'");
            return;
        }
        
        System.out.println("\nSe encontraron " + resultados.size() + " resultado(s):");
        for (Transaction t : resultados) {
            String tipo = t.isIncome() ? "[INGRESO]" : "[GASTO]";
            System.out.println("  - " + tipo + " " + t.getDescription() + 
                " | " + String.format("%.2f", t.getQuantity()) + " EUR");
        }
    }

    private static void verResumen(TransactionRepository repo) {
        System.out.println("\n--- RESUMEN FINANCIERO ---");
        
        List<Transaction> ingresos = repo.findByIncomeIsTrue();
        List<Transaction> gastos = repo.findByIncomeIsFalse();
        
        double totalIngresos = ingresos.stream()
            .mapToDouble(Transaction::getQuantity)
            .sum();
        
        double totalGastos = gastos.stream()
            .mapToDouble(Transaction::getQuantity)
            .sum();
        
        double balance = totalIngresos - totalGastos;
        
        System.out.println("\n  INGRESOS TOTALES:  " + String.format("%.2f", totalIngresos) + " EUR");
        System.out.println("  GASTOS TOTALES:    " + String.format("%.2f", totalGastos) + " EUR");
        System.out.println("  " + "-".repeat(40));
        
        if (balance >= 0) {
            System.out.println("  BALANCE: " + String.format("%.2f", balance) + " EUR (SUPERAVIT)");
        } else {
            System.out.println("  BALANCE: " + String.format("%.2f", balance) + " EUR (DEFICIT)");
        }
        
        System.out.println("\n  Numero de transacciones:");
        System.out.println("    - Ingresos: " + ingresos.size());
        System.out.println("    - Gastos: " + gastos.size());
    }

    private static void eliminarTransaccion(Scanner scanner, TransactionRepository repo) {
        System.out.println("\n--- ELIMINAR TRANSACCION ---");
        System.out.print("ID de la transaccion a eliminar: ");
        String id = scanner.nextLine();
        
        try {
            repo.deleteById(id);
            System.out.println("Transaccion eliminada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar la transaccion.");
        }
    }

    private static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}