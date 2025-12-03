package tema4add;

public class Entrenador {

    private int id;
    private String nombre;
    private String raza;
    private String equipo;
    private int n_partidos;

    // Constructor

    public Entrenador(String nombre, String equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEquipo() { return equipo; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    public int getPartidos() { return n_partidos; }
    public void setPartidos(int n_partidos) { this.n_partidos = n_partidos; }
}







