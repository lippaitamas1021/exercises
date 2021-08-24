package org.training360.finalexam;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {

    public EntityNotFoundException(long id, String entity) {
        super(URI.create("teams/not-found"),
                "Entity" + entity + " not found",
                Status.NOT_FOUND,
                String.format("Entity '%s' with ID: '%d' not found", entity, id));}
}
