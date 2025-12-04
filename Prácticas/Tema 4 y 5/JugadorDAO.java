package tema4add;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO {

    private Connection conn;

    public JugadorDAO() {
        conn = ConexionBD.getConexion();
    }

    // Inserta jugador
    public void insertarJugador(Jugador j) {
        String sql = "INSERT INTO jugador (nombre, posicion, herido, entrenador_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, j.getNombre());
            statement.setString(2, j.getPosicion());
            statement.setBoolean(3, j.isHerido());

            if (j.getEntrenadorId() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, j.getEntrenadorId());
            }

            statement.executeUpdate();
            System.out.println("Jugador insertado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al insertar jugador:");
            ex.printStackTrace();
        }
    }

    // Elimina jugador por ID
    public void eliminarJugador(int id) {
        String sql = "DELETE FROM jugador WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Jugador eliminado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al eliminar jugador:");
            ex.printStackTrace();
        }
    }

    // Lista todos los jugadores
    public ArrayList<Jugador> listarJugadores() {
        ArrayList<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugador";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Integer entrenadorId = rs.getObject("entrenador_id") == null ? null : rs.getInt("entrenador_id");

                Jugador j = new Jugador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getBoolean("herido"),
                        entrenadorId
                );
                lista.add(j);
            }

        } catch (SQLException ex) {
            System.err.println("Error al listar jugadores:");
            ex.printStackTrace();
        }

        return lista;
    }

    // Lista jugadores por entrenador
    public ArrayList<Jugador> listarPorEntrenador(int idEntrenador) {

        ArrayList<Jugador> lista = new ArrayList<>();

        String sql = "SELECT * FROM jugador WHERE entrenador_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrenador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getBoolean("herido"),
                        idEntrenador
                );
                lista.add(j);
            }

        } catch (SQLException ex) {
            System.err.println("Error al listar jugadores por entrenador:");
            ex.printStackTrace();
        }

        return lista;
    }

    // Lesionar jugador (herido = TRUE)
    public void lesionarJugador(int id) {
        String sql = "UPDATE jugador SET herido = TRUE WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Jugador lesionado correctamente.");

        } catch (SQLException ex) {
            System.err.println("Error al lesionar jugador:");
            ex.printStackTrace();
        }
    }
}

