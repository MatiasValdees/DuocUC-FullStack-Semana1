package cl.duoc.fullstack.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="PELICULAS")
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int annio;
    @ManyToOne
    @JoinColumn(name="genero_id")
    private GeneroEntity genero;
    @ManyToOne
    @JoinColumn(name="director_id")
    private DirectorEntity director;
    private String sinopsis;
}
