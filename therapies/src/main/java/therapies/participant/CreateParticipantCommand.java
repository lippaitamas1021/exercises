package therapies.participant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipantCommand {

    @NotBlank(message = "Participant's name must be completed")
    @Schema(description = "Participant's name", example = "Mila Kunis")
    private String name;}
