package multimedia.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from books")
public class BookControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateNewBook(){
        BookDTO result = template.postForObject("/api/books",
                new CreateBookCommand("Gárdonyi Géza", "Egri csillagok", LocalDate.of(1950,11,11)),
                BookDTO.class);
        assertEquals("Egri csillagok",result.getTitle());}


    @Test
    void testGetBooks(){
        template.postForObject("/api/books",
                new CreateBookCommand("Gárdonyi Géza", "Egri csillagok", LocalDate.of(1950,11,11)),
                BookDTO.class);
        template.postForObject("/api/books",
                new CreateBookCommand("Dan Brown", "Da Vinci kód", LocalDate.of(2005,10,10)),
                BookDTO.class);
        List<BookDTO> result = template.exchange(
                "/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>(){}).getBody();
        assertThat(result).extracting(BookDTO::getAuthor)
                .containsExactly("Gárdonyi Géza","Dan Brown");}


    @Test
    void testGetBookById(){
        BookDTO book = template.postForObject("/api/books",
                new CreateBookCommand("Dan Brown", "Da Vinci kód", LocalDate.of(2005,10,10)),
                BookDTO.class);
        List<BookDTO> result = template.exchange(
                "/api/books/" + book.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>(){}).getBody();
        assert result != null;
        assertEquals("Da Vinci kód", result.get(0).getTitle());}


    @Test
    void testUpdateBook() {
        BookDTO book1 = template.postForObject("/api/books",
                new CreateBookCommand("Dan Brown", "Da Vinci kód", LocalDate.of(2005,10,10)),
                BookDTO.class);
        BookDTO book2 = template.postForObject("/api/books",
                new CreateBookCommand("Gárdonyi Géza", "Egri csillagok", LocalDate.of(1950,11,11)),
                BookDTO.class);
        template.put("/api/books/{id}", new UpdateBookCommand("Da Vinci kód 2"), book1.getId());
        template.put("/api/books/{id}", new UpdateBookCommand("Egri pillangók"), book2.getId());
        List<BookDTO> result = template.exchange(
                "/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>(){}).getBody();
        assert result != null;
        assertEquals("Da Vinci kód 2", result.get(0).getTitle());
        assertEquals("Egri pillangók", result.get(1).getTitle());}


    @Test
    void testCreateBookWithInvalidTitle(){
        Problem result =
                template.postForObject("/api/books",
                        new CreateBookCommand("Dan Brown", "", LocalDate.of(2005,10,10)),
                        Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}


    @Test
    void testDeleteBookById() {
        BookDTO book = template.postForObject("/api/books",
                new CreateBookCommand("Dan Brown", "Da Vinci kód", LocalDate.of(2005,10,10)),
                BookDTO.class);
        template.delete("/api/books/{id}", book.getId());
        List<BookDTO> books = template.exchange(
                "/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>(){}).getBody();
        assertThat(books).isEmpty();}


    @Test
    void deleteAllTheBooks() {
        template.postForObject("/api/books",
                new CreateBookCommand("Gárdonyi Géza", "Egri csillagok", LocalDate.of(1950,11,11)),
                BookDTO.class);
        template.postForObject("/api/books",
                new CreateBookCommand("Dan Brown", "Da Vinci kód", LocalDate.of(2005,10,10)),
                BookDTO.class);
        template.delete("/api/books");
        List<BookDTO> books = template.exchange(
                "/api/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>(){}).getBody();
        assertThat(books).isEmpty();}}
