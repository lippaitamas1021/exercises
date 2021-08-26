package multimedia.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String author;

    private String title;

    private LocalDate date;


    public Book(String author, String title, LocalDate date) {
        this.author = author;
        this.title = title;
        this.date = date;}}
