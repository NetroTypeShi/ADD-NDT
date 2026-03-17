package com.example.practica9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Practica9Application {

    public static void main(String[] args) {
        System.setProperty("spring.data.mongodb.uri", 
            "mongodb+srv://andresmenduina_db_user:h4ncITppaK0qy482@accesoadatos.1q0y1co.mongodb.net/AccesoADatosDB?retryWrites=true&w=majority&appName=AccesoADatos");
        
        SpringApplication.run(Practica9Application.class, args);
    }

    @Bean
    public CommandLineRunner run(pokemonRepository pokemonRepository) {
        return args -> {
            try {
                // Cargar datos de prueba solo si la BD esta vacia
                if (pokemonRepository.count() == 0) {
                    System.out.println("\n[INFO] Cargando datos de prueba iniciales...\n");
                    cargarDatosPrueba(pokemonRepository);
                }
                hacerConsultas(pokemonRepository);
                
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

    private static void cargarDatosPrueba(pokemonRepository repo) {
        // Fechas para las pruebas
        Date fecha1 = convertToDate(LocalDate.now().minusDays(45));
        Date fecha2 = convertToDate(LocalDate.now().minusDays(30));
        Date fecha3 = convertToDate(LocalDate.now().minusDays(15));
        Date fecha4 = convertToDate(LocalDate.now().minusDays(5));
        Date fecha5 = convertToDate(LocalDate.now());
        
        // Crear transacciones de prueba
        pokemon p1 = new pokemon("1", "Pikachu", "Electrico", 10,"Ash", fecha1);
        pokemon p2 = new pokemon("2", "Raichu", "Electrico", 35,"Nico", fecha2);
        pokemon p3 = new pokemon("3", "Mondongo", "Agua", 80,"Netanyahu", fecha3);
        pokemon p4 = new pokemon("4", "Chiquito de la calzada", "Fuego", 23,"Ash", fecha4);
        pokemon p5 = new pokemon("5", "Ching chong", "Fuego", 43,"Netro", fecha5);
        pokemon p6 = new pokemon("6", "Chochin", "Planta", 12,"Guille", fecha4);
        
        repo.save(p1);
        repo.save(p2);
        repo.save(p3);
        repo.save(p4);
        repo.save(p5);
        repo.save(p6);
        
        System.out.println("Se han cargado 6 transacciones de prueba.\n");
    }
    
    private static Date convertToDate(LocalDate localDate) {
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    private static void hacerConsultas(pokemonRepository repo){
        //Pillar Todos
        List <pokemon> todos = repo.findAll();
        System.out.println("Todos los pokemon:");
        for(pokemon p : todos){
            System.out.println("Nombre:"+ p.getName());
            
       }
        
        System.out.println(" ");
        
        //Pillar de tipo Fuego
        List<pokemon> resultados = repo.findByTypeContaining("Fuego");
        System.out.println("Todos los pokemon tipo fuego:");
        for(pokemon p : resultados){
            System.out.println("Tipos fuego:" + p.getName());
        }
        
        System.out.println(" ");
         
        List<pokemon> mayores = repo.findByLevelGreaterThan(70);
        System.out.println("Todos los pokemon con un nivel mayor a 70:");
        for(pokemon p : mayores){
            System.out.println("Mayor a nivel 70:" + p.getName());
        }
        
        System.out.println(" ");
        
        List<pokemon> entre = repo.findByLevelBetween(10, 40);
        System.out.println("Todos los pokemon con un nivel entre 10 a 40:");
        for(pokemon p : entre){
            System.out.println("Nivel entre 10 a 40:" + p.getName());
        }
        
        System.out.println(" ");
        /*
        List<pokemon> entreFecha = repo.findByCaptureDateDateLessThanEqual(convertToDate(LocalDate.now().minusDays(18)));
        System.out.println("Todos los pokemon capturados antes del 27 febrero: ");
        for(pokemon p : entreFecha){
            System.out.println("Antes del antes del 27 febrero::" + p.getName());
        }
        */
        
        System.out.println(" ");
        
        List<pokemon> chu = repo.findByNameContaining("chu");
        System.out.println("Todos los pokemon con Pokemons con nombre chu:");
        for(pokemon p : chu){
            System.out.println("Pokemons Pokemons con nombre chu:" + p.getName());
        }
        
    }
    
    /*
    private static void menuInteractivo(pokemonRepository pokemonRepository) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            System.out.print("\nSelecciona una opcion: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 2:
                        verIngresos(pokemonRepository);
                        break;
                    case 3:
                        verGastos(pokemonRepository);
                        break;
                    case 4:
                        verTodasLasTransacciones(pokemonRepository);
                        break;
                    case 5:
                        buscarPorDescripcion(scanner, pokemonRepository);
                        break;
                    case 6:
                        verResumen(pokemonRepository);
                        break;
                    case 7:
                        eliminarTransaccion(scanner, pokemonRepository);
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
        System.out.println("1. Agregar nueva pokemon");
        System.out.println("2. Ver todos los ingresos");
        System.out.println("3. Ver todos los gastos");
        System.out.println("4. Ver todas las transacciones");
        System.out.println("5. Buscar por descripcion");
        System.out.println("6. Ver resumen (ingresos vs gastos)");
        System.out.println("7. Eliminar transaccion");
        System.out.println("8. Salir");
        System.out.println("=".repeat(50));
    }


    private static void verIngresos(pokemonRepository repo) {
        System.out.println("\n--- INGRESOS ---");
        List<pokemon> ingresos = repo.findByIncomeIsTrue();
        
        if (ingresos.isEmpty()) {
            System.out.println("No hay ingresos registrados.");
            return;
        }
        
        double total = 0;
        for (pokemon t : ingresos) {
            System.out.println("  - " + t.getDescription() + " | " + 
                String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
            total += t.getQuantity();
        }
        System.out.println("  " + "-".repeat(40));
        System.out.println("  TOTAL INGRESOS: " + String.format("%.2f", total) + " EUR");
    }

    private static void verGastos(pokemonRepository repo) {
        System.out.println("\n--- GASTOS ---");
        List<pokemon> gastos = repo.findByIncomeIsFalse();
        
        if (gastos.isEmpty()) {
            System.out.println("No hay gastos registrados.");
            return;
        }
        
        double total = 0;
        for (pokemon t : gastos) {
            System.out.println("  - " + t.getDescription() + " | " + 
                String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
            total += t.getQuantity();
        }
        System.out.println("  " + "-".repeat(40));
        System.out.println("  TOTAL GASTOS: " + String.format("%.2f", total) + " EUR");
    }

    private static void verTodasLasTransacciones(pokemonRepository repo) {
        System.out.println("\n--- TODAS LAS TRANSACCIONES ---");
        List<pokemon> todas = repo.findAll();
        
        if (todas.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
            return;
        }
        
        for (pokemon t : todas) {
            String tipo = t.isIncome() ? "[INGRESO]" : "[GASTO]";
            System.out.println("  - " + tipo + " " + t.getDescription() + 
                " | " + String.format("%.2f", t.getQuantity()) + " EUR | " + t.getDate());
        }
        System.out.println("\n  Total de transacciones: " + todas.size());
    }

    private static void buscarPorDescripcion(Scanner scanner, pokemonRepository repo) {
        System.out.println("\n--- BUSCAR POR DESCRIPCION ---");
        System.out.print("Palabra a buscar: ");
        String palabra = scanner.nextLine();
        
        List<pokemon> resultados = repo.findByDescriptionContaining(palabra);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron transacciones con '" + palabra + "'");
            return;
        }
        
        System.out.println("\nSe encontraron " + resultados.size() + " resultado(s):");
        for (pokemon t : resultados) {
            String tipo = t.isIncome() ? "[INGRESO]" : "[GASTO]";
            System.out.println("  - " + tipo + " " + t.getDescription() + 
                " | " + String.format("%.2f", t.getQuantity()) + " EUR");
        }
    }

    private static void verResumen(pokemonRepository repo) {
        System.out.println("\n--- RESUMEN FINANCIERO ---");
        
        List<pokemon> ingresos = repo.findByIncomeIsTrue();
        List<pokemon> gastos = repo.findByIncomeIsFalse();
        
        double totalIngresos = ingresos.stream()
            .mapToDouble(pokemon::getQuantity)
            .sum();
        
        double totalGastos = gastos.stream()
            .mapToDouble(pokemon::getQuantity)
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

    private static void eliminarTransaccion(Scanner scanner, pokemonRepository repo) {
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
*/
    
}
