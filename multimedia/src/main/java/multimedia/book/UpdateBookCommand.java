package multimedia.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookCommand {

    private int id;

    private String author;

    @NotBlank(message = "Title of the book must be completed")
    private String title;

    private LocalDate date;

    public UpdateBookCommand(String title) {
        this.title = title;}}
