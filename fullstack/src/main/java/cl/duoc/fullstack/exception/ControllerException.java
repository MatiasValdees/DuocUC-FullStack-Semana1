package cl.duoc.fullstack.exception;


import cl.duoc.fullstack.domain.exceptions.DirectorNoEncontrado;
import cl.duoc.fullstack.domain.exceptions.GeneroNoEncontradoException;
import cl.duoc.fullstack.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.fullstack.web.dtos.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(PeliculaNoEncontradaException.class)
    public ResponseEntity<ResponseWrapper<ExceptionResponse<String>>> handlePeliculaNotFound(PeliculaNoEncontradaException ex) {
        ExceptionResponse<String> response = ExceptionResponse.<String>builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();

        ResponseWrapper<ExceptionResponse<String>> responseWrapper = ResponseWrapper.<ExceptionResponse<String>>builder()
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneroNoEncontradoException.class)
    public ResponseEntity<ResponseWrapper<ExceptionResponse<String>>> handleGeneroNoEncontradoException(GeneroNoEncontradoException ex) {
        ExceptionResponse<String> response = ExceptionResponse.<String>builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();

                ResponseWrapper<ExceptionResponse<String>> responseWrapper = ResponseWrapper.<ExceptionResponse<String>>builder()
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DirectorNoEncontrado.class)
    public ResponseEntity<ResponseWrapper<ExceptionResponse<String>>> handleDirectorNoEncontrado(DirectorNoEncontrado ex) {
        ExceptionResponse<String> response = ExceptionResponse.<String>builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();
                ResponseWrapper<ExceptionResponse<String>> responseWrapper = ResponseWrapper.<ExceptionResponse<String>>builder()
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<ExceptionResponse<List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
    
        ExceptionResponse<List<String>> response = ExceptionResponse.<List<String>>builder()
                .code("VALIDATION_ERROR")
                .message(errors)
                .build();
    
        ResponseWrapper<ExceptionResponse<List<String>>> responseWrapper = ResponseWrapper.<ExceptionResponse<List<String>>>builder()
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();
    
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }
}
