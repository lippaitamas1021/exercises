package multimedia.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
@Tag(name = "Operations on books")
public class BookController {

    private BookService bookService;

    @GetMapping
    public List<BookDTO> listBooks() {
        return bookService.listBooks();}


    @GetMapping("/{id}")
    public BookDTO findBookByID(@PathVariable("id") int id) {
        return bookService.findBookById(id);}


    @PostMapping
    public BookDTO saveBook(@Valid @RequestBody CreateBookCommand command) {
        return bookService.saveBook(command);}


    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable("id") int id, @Valid @RequestBody UpdateBookCommand command) {
        return bookService.updateBook(id, command);}


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);}


    @DeleteMapping
    public void deleteAllTheBooks() {
        bookService.deleteAllTheBooks();}}
