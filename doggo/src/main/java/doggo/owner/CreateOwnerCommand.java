package doggo.owner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerCommand {

    @NotBlank(message = "Owner's name must be completed")
    @Schema(description = "Owner's name", example = "Mila Kunis")
    private String name;}