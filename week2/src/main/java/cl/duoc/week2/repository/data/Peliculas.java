package cl.duoc.week2.repository.data;

import java.util.ArrayList;
import java.util.List;

import cl.duoc.week2.domain.Director;
import cl.duoc.week2.domain.Genero;
import cl.duoc.week2.domain.Pelicula;

public class Peliculas {
    public static final List<Pelicula> peliculas = new ArrayList<>();
    static {
        Genero accion = new Genero(1, "Acción");
        Genero fantasia = new Genero(3, "Fantasía");
        Genero cienciaFiccion = new Genero(4, "Ciencia Ficción");

        Director director1 = new Director(1L, "Chris Columbus");
        Director director2 = new Director(2L, "Anthony Russo");

        peliculas.add(Pelicula.builder()
                .id(1L)
                .titulo("Harry Potter y la Piedra Filosofal")
                .año(2001)
                .genero(fantasia)
                .director(director1)
                .sinopsis("Un joven llamado Harry Potter descubre que es un mago.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(2L)
                .titulo("Avengers: Endgame")
                .año(2019)
                .genero(accion)
                .director(director2)
                .sinopsis("Los Vengadores luchan contra Thanos para salvar al universo.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(3L)
                .titulo("The Matrix")
                .año(1999)
                .genero(cienciaFiccion)
                .director(director2)
                .sinopsis("Un hombre descubre que la realidad que conoce es una simulación creada por máquinas.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(4L)
                .titulo("The Dark Knight")
                .año(2008)
                .genero(accion)
                .director(director2)
                .sinopsis("Batman debe enfrentarse al Joker, un villano con una visión caótica del mundo.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(5L)
                .titulo("Inception")
                .año(2010)
                .genero(cienciaFiccion)
                .director(director2)
                .sinopsis("Un ladrón especializado en robar secretos dentro del subconsciente de las personas.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(6L)
                .titulo("Avatar")
                .año(2009)
                .genero(accion)
                .director(director1)
                .sinopsis("Un hombre parapléjico se infiltra en un mundo alienígena para extraer recursos.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(7L)
                .titulo("Jurassic Park")
                .año(1993)
                .genero(accion)
                .director(director1)
                .sinopsis("Científicos reviven dinosaurios para un parque temático, pero todo se va de control.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(8L)
                .titulo("The Lord of the Rings: The Fellowship of the Ring")
                .año(2001)
                .genero(fantasia)
                .director(director1)
                .sinopsis("Un joven hobbit se embarca en una misión para destruir un anillo poderoso.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(9L)
                .titulo("Star Wars: A New Hope")
                .año(1977)
                .genero(cienciaFiccion)
                .director(director2)
                .sinopsis("Un joven llamado Luke Skywalker se une a la Rebelión para luchar contra el Imperio Galáctico.")
                .build());

        peliculas.add(Pelicula.builder()
                .id(10L)
                .titulo("Iron Man")
                .año(2008)
                .genero(accion)
                .director(director2)
                .sinopsis("Un millonario fabricante de armas se convierte en superhéroe después de ser secuestrado.")
                .build());
    }
}
