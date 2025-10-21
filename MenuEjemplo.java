import java.io.*;
import java.util.Scanner;

public class MenuEjemplo {
    static String ROOT = "cinema2000";
    static String USERS_FILE = ROOT + "/users.txt";
    static String REVIEWS_DIR = ROOT + "/reviews/";
    static String nombre;
    static int codigo;

    static final String[] PELICULAS = {
        "El Padrino", "Titanic", "Interestelar", "La La Land", "Forrest Gump"
    };
    // lista de peliculas

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // SCANNER PA LEER INPUTS
        new File(REVIEWS_DIR).mkdirs();
// crea el archivvo de las reviews
        int opcion;
        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1 - Crear usuario nuevo");
            System.out.println("2 - Eliminar usuario");
            System.out.println("3 - Añadir review");
            System.out.println("4 - Mostrar reviews de usuario");
            System.out.println("0 - Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                crearUsuario(sc);
            } else if (opcion == 2) {
                eliminarUsuario(sc);
            } else if (opcion == 3) {
                añadirReview(sc);
            } else if (opcion == 4) {
                mostrarReviews(sc);
            } else if (opcion == 0) {
                System.out.println("¡Hasta pronto!");
            } else {
                System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    static void crearUsuario(Scanner sc) {
        PeticionesConsola(sc);
        System.out.print("Introduce contraseña: ");
        String password = sc.nextLine();

        if (buscarUsuario(nombre, codigo)) {
            System.out.println("Ese usuario ya existe.");
            return;
        }

        try {
            FileWriter fw = new FileWriter(USERS_FILE, true);
            fw.write(nombre + "," + codigo + "," + password + "\n");
            // escribe el usuario junto con el nombre, contraseña y codigo
            fw.close();
            System.out.println("Usuario creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear usuario.");
            // si no funciona el try da el error
        }
    }

    static void eliminarUsuario(Scanner sc) {
        PeticionesConsola(sc);
        StringBuilder nuevosUsuarios = new StringBuilder();
        boolean eliminado = false;
        try {
            File file = new File(USERS_FILE);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                Scanner fileScanner = new Scanner(fr);
                // añadi el file reader y el scanner, pa leer el archivo y pa pedir inputs por así decirlo
                while (fileScanner.hasNextLine()) {
                    String linea = fileScanner.nextLine();
                    String[] partes = linea.split(",");
                    if (partes.length >= 2 && partes[0].equals(nombre) && Integer.parseInt(partes[1]) == codigo) {
                        eliminado = true;
                        // Eliminar el usuario
                    } else {
                        nuevosUsuarios.append(linea).append("\n");
                    }
                }
                fileScanner.close();
                fr.close();
                FileWriter fw = new FileWriter(file, false); // Sobrescribe
                fw.write(nuevosUsuarios.toString());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar usuario.");
        }

        // Elimina e archivo de reviews
        File f = new File(REVIEWS_DIR + nombre + "-" + codigo + ".txt");
        f.delete();

        if (eliminado) System.out.println("Usuario eliminado.");
        else System.out.println("Usuario no encontrado.");
    }

    static void añadirReview(Scanner sc) {
    PeticionesConsola(sc);

    if (!buscarUsuario(nombre, codigo)) {
        System.out.println("Usuario no encontrado.");
        return;
    }
    System.out.println("Elige una película para reseñar:");
    for (int i = 0; i < PELICULAS.length; i++) {
        System.out.println((i + 1) + " - " + PELICULAS[i]);
    }
    int peli = sc.nextInt();
    sc.nextLine();
    if (peli < 1 || peli > PELICULAS.length) {
        System.out.println("Película no válida.");
        return;
    }
    // Pide la reseña escrita
    System.out.print("Escribe tu reseña: ");
    String resena = sc.nextLine();

    // calificación del 1 al 10
    int calificacion;
    do {
        System.out.print("Introduce tu calificación (1-10): ");
        calificacion = sc.nextInt();
        sc.nextLine();
        if (calificacion < 1 || calificacion > 10) {
            System.out.println("Calificación no válida. Debe ser entre 1 y 10.");
        }
    } while (calificacion < 1 || calificacion > 10);

    String filename = REVIEWS_DIR + nombre + "-" + codigo + ".txt";
    try {
        FileWriter fw = new FileWriter(filename, true);
        fw.write(PELICULAS[peli - 1] + ": " + resena + " | Nota: " + calificacion + "\n");
        fw.close();
        System.out.println("Review añadida.");
    } catch (IOException e) {
        System.out.println("Error al guardar review.");
    }
}

    static void mostrarReviews(Scanner sc) {
        PeticionesConsola(sc);
        String filename = REVIEWS_DIR + nombre + "-" + codigo + ".txt";
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("No hay reviews para este usuario.");
            return;
        }
        try {
            FileReader fr = new FileReader(f);
            Scanner fileScanner = new Scanner(fr);
            System.out.println("Reviews de " + nombre + ":");
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                System.out.println("- " + linea);
            }
            fileScanner.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error al leer reviews.");
        }
    }
    
    public static void PeticionesConsola(Scanner sc){
        System.out.print("Introduce nombre: ");
        nombre = sc.nextLine();
        System.out.print("Introduce código: ");
        codigo = sc.nextInt();
        sc.nextLine();
    }

    static boolean buscarUsuario(String nombre, int codigo) {
        try {
            File file = new File(USERS_FILE);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                Scanner fileScanner = new Scanner(fr);
                // eto e pa crea un scanner pa leer el archivo
                while (fileScanner.hasNextLine()) {
                    String linea = fileScanner.nextLine();
                    String[] partes = linea.split(",");
                    if (partes.length >= 2 && partes[0].equals(nombre) && Integer.parseInt(partes[1]) == codigo) {
                        fileScanner.close();
                        fr.close();
                        return true;
                    }
                }
                fileScanner.close();
                fr.close();
            }
        } catch (IOException e) {
        }
        return false;
    }
}