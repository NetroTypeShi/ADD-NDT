package tema4add;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();

        int opcion;

        do {
            System.out.println("MENÚ PRINCIPAL BLOOD BOWL");
            System.out.println("1. Añadir entrenador");
            System.out.println("2. Añadir jugador");
            System.out.println("3. Eliminar entrenador");
            System.out.println("4. Eliminar jugador");
            System.out.println("5. Listar entrenadores");
            System.out.println("6. Listar jugadores de un entrenador");
            System.out.println("7. Lesionar jugador");
            System.out.println("8. (Opcional) Jugar partido");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                //Añadir entrenador
                case 1:
                    System.out.print("Nombre del entrenador: ");
                    String nombreE = sc.nextLine();

                    System.out.print("Raza del entrenador: ");
                    String raza = sc.nextLine();

                    System.out.print("Número de partidos: ");
                    int partidos = sc.nextInt();
                    sc.nextLine();

                    Entrenador e = new Entrenador(nombreE, raza, partidos);
                    entrenadorDAO.insertarEntrenador(e);
                    break;

                // Añadir jugadir
                case 2:
                    System.out.print("Nombre del jugador: ");
                    String nombreJ = sc.nextLine();

                    System.out.print("Posición del jugador: ");
                    String posicion = sc.nextLine();

                    System.out.print("¿Está herido? (true/false): ");
                    boolean herido = sc.nextBoolean();

                    System.out.print("ID del entrenador (o 0 para ninguno): ");
                    int idEnt = sc.nextInt();
                    sc.nextLine();

                    Integer idEntrenador = (idEnt == 0) ? null : idEnt;

                    Jugador j = new Jugador(nombreJ, posicion, herido, idEntrenador);
                    jugadorDAO.insertarJugador(j);
                    break;

                // Eliminar entrenador
                case 3:
                    System.out.print("ID del entrenador a eliminar: ");
                    int idEliminarE = sc.nextInt();
                    sc.nextLine();

                    entrenadorDAO.eliminarEntrenador(idEliminarE);
                    break;

                // Eliminar jugador
                case 4:
                    System.out.print("ID del jugador a eliminar: ");
                    int idEliminarJ = sc.nextInt();
                    sc.nextLine();

                    jugadorDAO.eliminarJugador(idEliminarJ);
                    break;

                // Listar entrenadores
                case 5:
                    ArrayList<Entrenador> entrenadores = entrenadorDAO.listarEntrenadores();
                    System.out.println("ENTRENADORES");
                    for (Entrenador en : entrenadores) {
                        System.out.println("ID: " + en.getId() +
                                           " | Nombre: " + en.getNombre() +
                                           " | Raza: " + en.getRaza() +
                                           " | Partidos: " + en.getN_partidos());
                    }
                    break;

                // 6. Listar jugadores por entrenador
                case 6:
                    System.out.print("ID del entrenador: ");
                    int idListar = sc.nextInt();
                    sc.nextLine();

                    ArrayList<Jugador> jugadores = jugadorDAO.listarPorEntrenador(idListar);

                    System.out.println("JUGADORES DEL ENTRENADOR" + idListar + " ---");

                    for (Jugador ju : jugadores) {
                        System.out.println("ID: " + ju.getId() +
                                           " | Nombre: " + ju.getNombre() +
                                           " | Posición: " + ju.getPosicion() +
                                           " | Herido: " + ju.isHerido());
                    }

                    break;

                // Lesionar jugador
                case 7:
                    System.out.print("ID del jugador a lesionar: ");
                    int idLesion = sc.nextInt();
                    sc.nextLine();

                    jugadorDAO.lesionarJugador(idLesion);
                    break;

                // Jugar partido → sumar 1 partido a 2 entrenadores
                case 8:
                    System.out.print("ID del primer entrenador: ");
                    int id1 = sc.nextInt();

                    System.out.print("ID del segundo entrenador: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();

                    entrenadorDAO.sumarPartido(id1);
                    entrenadorDAO.sumarPartido(id2);

                    System.out.println("Partido registrado correctamente.");
                    break;

                case 0:
                    System.out.println("Cerrando programa...");
                    ConexionBD.cerrarConexion();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}

