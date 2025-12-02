package tema4add;

public class Jugador {

    private int id;
    private String nombre;
    private String posicion;
    private boolean herido;
    private int entrenadorId;

    // Constructor

    public Jugador(String nombre, String posicion, double valor, boolean herido, int entrenadorId) {
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

    public int getEntrenadorId() { return entrenadorId; }
    public void setEntrenadorId(int entrenadorId) { this.entrenadorId = entrenadorId; }
}






