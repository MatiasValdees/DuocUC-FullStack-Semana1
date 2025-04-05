package cl.duoc.week2.config.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.duoc.week2.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.week2.web.dtos.ResponseWrapper;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(PeliculaNoEncontradaException.class)
    public ResponseEntity<ResponseWrapper<ExceptionResponse>> handlePeliculaNotFound(PeliculaNoEncontradaException ex) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();

        ResponseWrapper<ExceptionResponse> responseWrapper = ResponseWrapper.<ExceptionResponse>builder()
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

}
