package org.training360.finalexam.players;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerCommand {

    @NotBlank(message = "Player's name must be completed")
    @Schema(description = "Name of the player", example = "Király Gábor")
    private String name;

    @Schema(description = "Player's date of birth", example = "1990.12.12")
    private LocalDate dateOfBirth;

    @Schema(description = "Position of the player", example = "GOALKEEPER")
    private PositionType position;
}
