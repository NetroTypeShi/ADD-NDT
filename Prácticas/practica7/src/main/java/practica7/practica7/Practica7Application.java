package practica7.practica7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import practica7.practica7.model.Obra;
import practica7.practica7.model.Sesion;
import practica7.practica7.service.ObraService;
import practica7.practica7.service.SesionService;
import java.util.Scanner;

@SpringBootApplication
public class Practica7Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Practica7Application.class, args);
		
		ObraService obraService = context.getBean(ObraService.class);
		SesionService sesionService = context.getBean(SesionService.class);
		
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			mostrarMenu();
			int opcion = pedirEntero(scanner, "Elige una opción: ");
			
			switch(opcion) {
				case 1:
					agregarObra(scanner, obraService);
					break;
				case 2:
					listarObras(obraService);
					break;
				case 3:
					eliminarObra(scanner, obraService, sesionService);
					break;
				case 4:
					agregarSesion(scanner, obraService, sesionService);
					break;
				case 5:
					listarSesiones(sesionService);
					break;
				case 6:
					eliminarSesion(scanner, sesionService);
					break;
				case 7:
					actualizarSesion(scanner, sesionService);
					break;
				case 8:
					salir = true;
					System.out.println("\n✓ ¡Hasta luego!\n");
					break;
				default:
					System.out.println("\n✗ Opción no válida\n");
			}
		}
		scanner.close();
	}
	
	//MENÚ
	static void mostrarMenu() {
		System.out.println("\n========== MENU TEATRO ==========");
		System.out.println("1. Agregar nueva obra");
		System.out.println("2. Listar todas las obras");
		System.out.println("3. Eliminar una obra");
		System.out.println("4. Agregar nueva sesión");
		System.out.println("5. Listar todas las sesiones");
		System.out.println("6. Eliminar una sesión");
		System.out.println("7. Actualizar hora de sesión");
		System.out.println("8. Salir");
		System.out.println("================================");
	}
	
	// ==================== OBRAS ====================
	static void agregarObra(Scanner scanner, ObraService obraService) {
		System.out.println("\n--- AGREGAR NUEVA OBRA ---");
		scanner.nextLine(); // ← AÑADE ESTO AL PRINCIPIO
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Autor: ");
		String autor = scanner.nextLine();
		System.out.print("Género: ");
		String genero = scanner.nextLine();
		System.out.print("Duración (minutos): ");
		int duracion = pedirEntero(scanner, "");
		scanner.nextLine(); // Limpiar buffer
	
		Obra obra = new Obra(nombre, autor, genero, duracion);
		obraService.insertarObra(obra);
	}
	
	static void listarObras(ObraService obraService) {
		System.out.println("\n--- LISTADO DE OBRAS ---");
		var obras = obraService.obtenerTodasLasObras();
		if (obras.isEmpty()) {
			System.out.println("No hay obras registradas");
		} else {
			obras.forEach(o -> System.out.println("  ID: " + o.getId() + " | " + o.getNombre() + " - " + o.getAutor() + " (" + o.getGenero() + ") " + o.getDuracion() + "min"));
		}
	}
	
	static void eliminarObra(Scanner scanner, ObraService obraService, SesionService sesionService) {
		System.out.println("\n--- ELIMINAR OBRA ---");
		listarObras(obraService);
		long id = pedirLong(scanner, "ID de la obra a eliminar: ");
		Obra obra = obraService.obtenerObraPorId(id);
		if (obra != null) {
			// Primero eliminar las sesiones asociadas
			var sesiones = sesionService.obtenerTodasLasSesiones();
			sesiones.stream()
				.filter(s -> s.getObra().getId().equals(id))
				.forEach(sesionService::borrarSesion);
			obraService.borrarObra(obra);
		}
	}
	
	// ==================== SESIONES ====================
	static void agregarSesion(Scanner scanner, ObraService obraService, SesionService sesionService) {
		System.out.println("\n--- AGREGAR NUEVA SESIÓN ---");
		listarObras(obraService);
		long idObra = pedirLong(scanner, "ID de la obra: ");
		Obra obra = obraService.obtenerObraPorId(idObra);
		
		if (obra != null) {
			scanner.nextLine(); // Limpiar buffer
			System.out.print("Día (Lunes, Martes, etc): ");
			String dia = scanner.nextLine();
			System.out.print("Hora (HH:MM): ");
			String hora = scanner.nextLine();
			System.out.print("Sala (número): ");
			int sala = pedirEntero(scanner, "");
			scanner.nextLine(); // Limpiar buffer
			
			Sesion sesion = new Sesion(hora, dia, sala, obra);
			sesionService.insertarSesion(sesion);
		} else {
			System.out.println("✗ Obra no encontrada");
		}
	}
	
	static void listarSesiones(SesionService sesionService) {
		System.out.println("\n--- LISTADO DE SESIONES ---");
		var sesiones = sesionService.obtenerTodasLasSesiones();
		if (sesiones.isEmpty()) {
			System.out.println("No hay sesiones registradas");
		} else {
			sesiones.forEach(s -> System.out.println("  ID: " + s.getId() + " | " + s.getDia() + " " + s.getHora() + " - Sala " + s.getSala() + " | Obra: " + s.getObra().getNombre()));
		}
	}
	
	static void eliminarSesion(Scanner scanner, SesionService sesionService) {
		System.out.println("\n--- ELIMINAR SESIÓN ---");
		listarSesiones(sesionService);
		long id = pedirLong(scanner, "ID de la sesión a eliminar: ");
		Sesion sesion = sesionService.obtenerSesionPorId(id);
		if (sesion != null) {
			sesionService.borrarSesion(sesion);
		}
	}
	
	static void actualizarSesion(Scanner scanner, SesionService sesionService) {
		System.out.println("\n--- ACTUALIZAR HORA DE SESIÓN ---");
		listarSesiones(sesionService);
		long id = pedirLong(scanner, "ID de la sesión: ");
		scanner.nextLine(); // Limpiar buffer
		System.out.print("Nueva hora (HH:MM): ");
		String nuevaHora = scanner.nextLine();
		sesionService.actualizarHoraSesion(id, nuevaHora);
	}
	
	// ==================== UTILIDADES ====================
	static int pedirEntero(Scanner scanner, String mensaje) {
		System.out.print(mensaje);
		while (!scanner.hasNextInt()) {
			System.out.print("Ingresa un número válido: ");
			scanner.nextLine();
		}
		return scanner.nextInt();
	}
	
	static long pedirLong(Scanner scanner, String mensaje) {
		System.out.print(mensaje);
		while (!scanner.hasNextLong()) {
			System.out.print("Ingresa un ID válido: ");
			scanner.nextLine();
		}
		return scanner.nextLong();
	}
}