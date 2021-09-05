package football.teams;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeamCommand {

    @NotBlank(message = "Name of the team must be completed")
    @Schema(name = "Name of the team", example = "Manchester City")
    private String name;}