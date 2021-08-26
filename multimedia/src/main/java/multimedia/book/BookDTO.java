package multimedia.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private int id;

    private String author;

    private String title;

    private LocalDate date;
}
