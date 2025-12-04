package tema4add;

public class Jugador {

    private int id;
    private String nombre;
    private String posicion;
    private boolean herido;
    private Integer entrenadorId; // puede ser null

    // Constructor jugador sin ID (pa inserta en la bd)
    public Jugador(String nombre, String posicion, boolean herido, Integer entrenadorId) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.herido = herido;
        this.entrenadorId = entrenadorId;
    }

    // Constructor con ID (pa leer de BD)
    public Jugador(int id, String nombre, String posicion, boolean herido, Integer entrenadorId) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.herido = herido;
        this.entrenadorId = entrenadorId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public boolean isHerido() { return herido; }
    public void setHerido(boolean herido) { this.herido = herido; }

    public Integer getEntrenadorId() { return entrenadorId; }
    public void setEntrenadorId(Integer entrenadorId) { this.entrenadorId = entrenadorId; }
}







