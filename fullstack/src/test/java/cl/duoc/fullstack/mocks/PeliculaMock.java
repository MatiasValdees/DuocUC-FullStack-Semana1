package cl.duoc.fullstack.mocks;


import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.web.dtos.PeliculaCreateRequest;

public class PeliculaMock {
    public static final PeliculaEntity Pelicula1 =
            PeliculaEntity.builder()
                    .id(1L)
                    .titulo("El Padrino")
                    .annio(1972)
                    .genero(GeneroMock.Genero1)
                    .director(DirectorMock.Director1)
                    .sinopsis("La historia de una familia mafiosa en los Estados Unidos.")
                    .build();
    public static final PeliculaEntity Pelicula10 =
            PeliculaEntity.builder()
                    .titulo("El Padrino")
                    .annio(1972)
                    .genero(GeneroMock.Genero1)
                    .director(DirectorMock.Director1)
                    .sinopsis("La historia de una familia mafiosa en los Estados Unidos.")
                    .build();
    public static final PeliculaCreateRequest peliculaRequest=new PeliculaCreateRequest(
            "El Padrino",
            1972,
            1L,
            1L,
            "La historia de una familia mafiosa en los Estados Unidos."
    );
    public static final PeliculaEntity pelicula2 = PeliculaEntity.builder()
            .id(2L)
            .titulo("El Padrino")
            .annio(1972)
            .genero(GeneroMock.Genero1)
            .director(DirectorMock.Director1)
            .sinopsis("La historia de una familia mafiosa en los Estados Unidos.")
            .build();

}
