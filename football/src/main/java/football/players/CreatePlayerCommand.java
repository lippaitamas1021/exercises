package football.players;

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
    @Schema(description = "Player's name", example = "Sallai Viktor")
    private String name;

    @Schema(description = "Player's birth date", example = "1990.12.12.")
    private LocalDate birthDate;

    @Schema(description = "Player's position", example = "GOALKEEPER")
    private PositionType position;}