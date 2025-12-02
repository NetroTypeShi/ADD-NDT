/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4add;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection conn = ConexionBD.getConexion();

        if (conn != null) {
            System.out.println("La conexión funciona.");
        }

        ConexionBD.cerrarConexion();
    }
}
