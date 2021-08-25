package doggo;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class NotFoundException extends AbstractThrowableProblem {

    public NotFoundException(int id) {
        super(
                URI.create("not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Entity not found with ID '%d'", id));
    }
}