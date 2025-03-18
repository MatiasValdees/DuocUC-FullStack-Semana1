package domain.exceptions;

public class PeliculaNoEncontradaException extends RuntimeException {
    public PeliculaNoEncontradaException (String field,String value){
        super(String.format("No se ha encontrado película con %s : %s",field,value));
    }
}
