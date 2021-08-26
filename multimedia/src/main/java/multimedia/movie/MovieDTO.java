package multimedia.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private int id;

    private String title;

    private String director;

    private String studio;
}
