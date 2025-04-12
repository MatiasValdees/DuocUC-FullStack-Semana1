package cl.duoc.week2.web.dtos;

import cl.duoc.week2.domain.PeliculaEntity;

public record PeliculaResponse(
    Long id,
    String titulo,
    int año,
    String director,
    String genero,
    String sinopsis
) {

    public static PeliculaResponse fromDomain(PeliculaEntity domain){
        return new PeliculaResponse(domain.getId(),
                                    domain.getTitulo(),
                                    domain.getAnnio(),
                                    domain.getDirector().getNombre(),
                                    domain.getGenero().getNombre(),
                                    domain.getSinopsis()
                                    );
    }
}
