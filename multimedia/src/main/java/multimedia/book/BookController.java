package multimedia.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @Tag(name = "GET")
    @Operation(summary = "Listing books", description = "This option is for listing all the books")
    public List<BookDTO> listBooks() {return bookService.listBooks();}


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a book by ID", description = "This option is for finding a book by ID")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public BookDTO findBookByID(@PathVariable("id") int id) {return bookService.findBookById(id);}


    @PostMapping
    @Tag(name = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Saving a new book", description = "This option is for saving a new book")
    @ApiResponse(responseCode = "201", description = "New book has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a new book")
    public BookDTO saveBook(@Valid @RequestBody CreateBookCommand command) {return bookService.saveBook(command);}


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating a book", description = "This option is for updating a book by ID")
    @ApiResponse(responseCode = "200", description = "Book has been updated")
    @ApiResponse(responseCode = "400", description = "Book update is not allowed")
    public BookDTO updateBook(@PathVariable("id") int id, @Valid @RequestBody UpdateBookCommand command) {
        return bookService.updateBook(id, command);}


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a book", description = "This option is for deleting a book by ID")
    @ApiResponse(responseCode = "204", description = "Book has been deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public void deleteBook(@PathVariable("id") int id) {bookService.deleteBook(id);}


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting all the books", description = "This option is for deleting all the books")
    @ApiResponse(responseCode = "204", description = "Book has been deleted")
    public void deleteAllTheBooks() {bookService.deleteAllTheBooks();}}