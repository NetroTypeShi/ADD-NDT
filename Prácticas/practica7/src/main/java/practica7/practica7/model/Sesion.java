package practica7.practica7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora", nullable = false)
    private String hora;

    @Column(name = "dia", nullable = false)
    private String dia;

    @Column(name = "sala", nullable = false)
    private Integer sala;

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obra;

    // Constructores
    public Sesion() {
    }

    public Sesion(String hora, String dia, Integer sala, Obra obra) {
        this.hora = hora;
        this.dia = dia;
        this.sala = sala;
        this.obra = obra;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "id=" + id +
                ", hora='" + hora + '\'' +
                ", dia='" + dia + '\'' +
                ", sala=" + sala +
                ", id_obra=" + obra.getId() +
                '}';
    }
}
