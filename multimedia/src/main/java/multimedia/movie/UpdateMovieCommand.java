package multimedia.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieCommand {

    @NotBlank(message = "Title of the movie must be completed")
    @Schema(description = "Title of the movie", example = "Revolver")
    private String title;

    @NotBlank(message = "Director of the movie must be completed")
    @Schema(description = "Director of the movie", example = "Guy Ritchie")
    private String director;}