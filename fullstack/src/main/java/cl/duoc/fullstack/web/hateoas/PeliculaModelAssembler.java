package cl.duoc.fullstack.web.hateoas;

import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.web.PeliculaController;
import cl.duoc.fullstack.web.dtos.PeliculaResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<PeliculaEntity, EntityModel<PeliculaResponse>> {

    @Override
    public EntityModel<PeliculaResponse> toModel(PeliculaEntity pelicula) {
        PeliculaResponse response = PeliculaResponse.fromDomain(pelicula);

        return EntityModel.of(
                response,
                linkTo(methodOn(PeliculaController.class)
                        .getById(pelicula.getId()))
                        .withSelfRel(),

                linkTo(methodOn(PeliculaController.class)
                        .delete(pelicula.getId()))
                        .withRel("delete"),

                linkTo(methodOn(PeliculaController.class)
                        .update(null))
                        .withRel("update"),

                linkTo(methodOn(PeliculaController.class)
                        .getAll())
                        .withRel("all")
        );
    }
}