/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4add;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static Connection conexion = null;

    // Método para abrir la conexión
    public static Connection getConexion() {

        try {
            if (conexion == null || conexion.isClosed()) {

                // 1. Cargar driver
                Class.forName(ConfiguracionXML.getDriver());

                // 2. Crear la conexión usando los valores del XML
                conexion = DriverManager.getConnection(
                        ConfiguracionXML.getUrl(),
                        ConfiguracionXML.getUsuario(),
                        ConfiguracionXML.getPassword()
                );

                System.out.println("Conexión a la base de datos realizada correctamente.");
            }

        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }

        return conexion;
    }

    // Cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión:");
            e.printStackTrace();
        }
    }
}