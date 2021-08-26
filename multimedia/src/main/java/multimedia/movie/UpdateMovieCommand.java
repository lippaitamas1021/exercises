package multimedia.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieCommand {

    @NotBlank(message = "Title of the movie must be completed")
    private String title;

    @NotBlank(message = "Director of the movie must be completed")
    private String director;

    @NotBlank(message = "Studio of the movie must be completed")
    private String studio;}
