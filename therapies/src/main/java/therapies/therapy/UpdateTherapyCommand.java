package therapies.therapy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTherapyCommand {

    @NotBlank(message = "Therapist must be given")
    @Schema(description = "Theapist of the therapy", example = "Anne Hathaway")
    private String therapist;}