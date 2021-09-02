package multimedia.book;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Author of the book", example = "Dan Brown")
    private String author;

    @NotBlank(message = "Title of the book must be completed")
    @Schema(description = "Title of the book", example = "Da Vinci kód")
    private String title;}