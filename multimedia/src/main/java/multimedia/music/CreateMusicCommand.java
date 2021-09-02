package multimedia.music;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicCommand {

    @NotBlank(message = "Performer of the music must be completed")
    @Schema(description = "Performer of the music", example = "Kiesza")
    private String performer;

    @NotBlank(message = "Title of the music must be completed")
    @Schema(description = "Title of the music", example = "Hideaway")
    private String title;

    @NotBlank(message = "Genre of the music must be completed")
    @Schema(description = "Genre of the music", example = "Pop")
    private String genre;}