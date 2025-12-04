package tema4add;

import java.sql.*;
import java.util.ArrayList;

public class EntrenadorDAO {

    private Connection conn;

    public EntrenadorDAO() {
        conn = ConexionBD.getConexion();
    }

    //Insertar entrenador
    public void insertarEntrenador(Entrenador e) {
        String sql = "INSERT INTO entrenador (nombre, raza, n_partidos) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getRaza());
            stmt.setInt(3, e.getN_partidos());

            stmt.executeUpdate();
            System.out.println("Entrenador insertado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al insertar entrenador:");
            ex.printStackTrace();
        }
    }

    // Eliminar entrenador por ID
    public void eliminarEntrenador(int id) {
        String sql = "DELETE FROM entrenador WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Entrenador eliminado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al eliminar entrenador:");
            ex.printStackTrace();
        }
    }

    // Lista todos los entrenadores
    public ArrayList<Entrenador> listarEntrenadores() {
        ArrayList<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrenador";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Entrenador e = new Entrenador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("raza"),
                        rs.getInt("n_partidos")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Error al listar entrenadores:");
            ex.printStackTrace();
        }

        return lista;
    }

    // Sumar 1 partido a un entrenador
    public void sumarPartido(int id) {
        String sql = "UPDATE entrenador SET n_partidos = n_partidos + 1 WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Partido sumado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al sumar partido:");
            ex.printStackTrace();
        }
    }
}

