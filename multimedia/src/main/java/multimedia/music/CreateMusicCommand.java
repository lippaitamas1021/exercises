package multimedia.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicCommand {

    @NotBlank(message = "Performer of the music must be completed")
    private String performer;

    @NotBlank(message = "Title of the music must be completed")
    private String title;

    @NotBlank(message = "Genre of the music must be completed")
    private String genre;}