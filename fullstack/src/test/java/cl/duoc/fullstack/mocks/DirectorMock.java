package cl.duoc.fullstack.mocks;


import cl.duoc.fullstack.domain.DirectorEntity;

public class DirectorMock {
    public final static DirectorEntity Director1 =
            DirectorEntity.builder()
                    .id(1L)
                    .nombre("Francis Ford Coppola")
                    .build();
}
