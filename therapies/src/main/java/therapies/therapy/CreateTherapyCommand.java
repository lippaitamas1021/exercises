package therapies.therapy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTherapyCommand {

    @NotBlank(message = "Therapist must be given")
    @Schema(description = "Theapist of the therapy", example = "Anne Hathaway")
    private String therapist;


    @NotBlank(message = "Place of the therapy must be given")
    @Schema(description = "Place of the therapy", example = "Aréna")
    private String place;


    @NotBlank(message = "Date of the therapy must be given")
    @Schema(description = "Date of the therapy", example = "20210901")
    private String date;}