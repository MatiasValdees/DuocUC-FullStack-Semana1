package cl.duoc.week2.web.dtos;

import domain.Pelicula;

public record PeliculaResponse(
    Long id,
    String titulo,
    int año,
    String director,
    String genero,
    String sinopsis
) {

    public static PeliculaResponse fromDomain(Pelicula domain){
        return new PeliculaResponse(domain.getId(),
                                    domain.getTitulo(),
                                    domain.getAño(),
                                    domain.getDirector().getNombre(),
                                    domain.getGenero().getNombre(),
                                    domain.getSinopsis()
                                    );
    }
}
