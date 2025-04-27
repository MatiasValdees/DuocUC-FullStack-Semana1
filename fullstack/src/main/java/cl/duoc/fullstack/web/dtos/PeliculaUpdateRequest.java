package cl.duoc.fullstack.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PeliculaUpdateRequest(
    @NotNull(message = "El campo id es obligatorio")
    @Positive(message = "El campo id debe ser un número positivo")
    Long id,
    
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

    @Size(max = 500, message = "El campo sinopsis no puede exceder los 500 caracteres")
    String sinopsis
) {

}
