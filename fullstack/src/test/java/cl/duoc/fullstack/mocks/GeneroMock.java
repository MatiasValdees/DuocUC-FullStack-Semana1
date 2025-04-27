package cl.duoc.fullstack.mocks;


import cl.duoc.fullstack.domain.GeneroEntity;

public class GeneroMock {
    public final static GeneroEntity Genero1 =
            GeneroEntity.builder()
                    .id(1L)
                    .nombre("Acción")
                    .build();
}
