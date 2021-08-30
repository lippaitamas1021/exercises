package therapies.participant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParticipantCommand {

    @NotBlank(message = "Participant's name must be completed")
    @Schema(description = "Participant's name", example = "Margot Robbie")
    private String name;}