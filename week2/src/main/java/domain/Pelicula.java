package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pelicula {
    private Long id;
    private String titulo;
    private int año;
    private Genero genero;
    private Director director;
    private String sinopsis;
}
