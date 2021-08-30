package therapies.therapy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExistingParticipantCommand {

    @NotNull
    @Schema(name = "Participant's ID", example = "1")
    private int id;}