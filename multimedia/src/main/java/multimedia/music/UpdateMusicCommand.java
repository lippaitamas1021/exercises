package multimedia.music;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMusicCommand {

    @NotBlank(message = "Performer of the music must be completed")
    @Schema(description = "Performer of the track", example = "Depeche Mode")
    private String performer;

    @NotBlank(message = "Title of the music must be completed")
    @Schema(description = "Title of the track", example = "Strangelove")
    private String title;

    @NotBlank(message = "Genre of the music must be completed")
    @Schema(description = "Genre of the track", example = "pop")
    private String genre;}