package multimedia.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookCommand {

    @NotBlank(message = "Author of the book must be given")
    @Schema(description = "Author of the book", example = "Gárdonyi Géza")
    private String author;

    @NotBlank(message = "Title of the book must be completed")
    @Schema(description = "Title of the book", example = "Egri csillagok")
    private String title;}