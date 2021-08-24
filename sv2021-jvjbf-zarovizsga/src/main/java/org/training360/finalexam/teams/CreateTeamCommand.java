package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamCommand {

    @NotBlank(message = "Name of the tem must be completed")
    @Schema(description = "Name of the team", example = "PSG")
    private String name;
}
