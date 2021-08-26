package multimedia.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand {

    @NotBlank(message = "Author of the book must be completed")
    private String author;

    @NotBlank(message = "Title of the book must be completed")
    private String title;

    private LocalDate date;}