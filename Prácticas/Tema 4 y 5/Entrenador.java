package tema4add;

public class Entrenador {

    private int id;
    private String nombre;
    private String raza;
    private int n_partidos;

    // Constructor sin ID (pa insertar)
    public Entrenador(String nombre, String raza, int n_partidos) {
        this.nombre = nombre;
        this.raza = raza;
        this.n_partidos = n_partidos;
    }

    // Constructor con ID (pa leer de la BD)
    public Entrenador(int id, String nombre, String raza, int n_partidos) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.n_partidos = n_partidos;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getN_partidos() { return n_partidos; }
    public void setN_partidos(int n_partidos) { this.n_partidos = n_partidos; }
}







