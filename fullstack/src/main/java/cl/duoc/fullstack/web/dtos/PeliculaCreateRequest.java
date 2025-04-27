package cl.duoc.fullstack.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PeliculaCreateRequest(
    @NotBlank(message = "El campo titulo es obligatorio")
    String titulo,

    @NotNull(message = "El campo año es obligatorio")
    @Positive(message = "El campo año debe ser un número positivo")
    Integer annio,

    @NotNull(message = "El campo generoId es obligatorio")
    @Positive(message = "El campo generoId debe ser un número positivo")
    Long generoId,

    @NotNull(message = "El campo directorId es obligatorio")
    @Positive(message = "El campo directorId debe ser un número positivo")
    Long directorId,

    @NotBlank(message = "El campo sinopsis es obligatorio")
    String sinopsis
) {

}
