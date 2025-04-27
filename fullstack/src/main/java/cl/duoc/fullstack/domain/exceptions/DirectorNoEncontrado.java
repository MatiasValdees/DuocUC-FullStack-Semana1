package cl.duoc.fullstack.domain.exceptions;

public class DirectorNoEncontrado extends RuntimeException {
    public DirectorNoEncontrado (String field,String value){
        super(String.format("No se ha encontrado director con %s : %s",field,value));
    }

}
