package cl.duoc.week2.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import domain.exceptions.PeliculaNoEncontradaException;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(PeliculaNoEncontradaException.class )
    public ResponseEntity<ExceptionResponse> handlePeliculaNotFound(PeliculaNoEncontradaException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
