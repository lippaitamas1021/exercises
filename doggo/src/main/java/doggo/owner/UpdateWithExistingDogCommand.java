package doggo.owner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWithExistingDogCommand {

    @NotNull
    @Schema(name = "ID of the dog must be given", example = "1")
    private int id;}