package multimedia.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from movie")
public class MovieControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateNewMovie(){
        MovieDTO result = template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris1", "Rudolf Péter"),
                MovieDTO.class);
        assertEquals("Üvegtigris1",result.getTitle());}


    @Test
    void testGetMovies(){
        template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris1", "Rudolf Péter"),
                MovieDTO.class);
        template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris2", "Rudolf Péter"),
                MovieDTO.class);
        List<MovieDTO> result = template.exchange(
                "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDTO>>(){}).getBody();
        assertThat(result).extracting(MovieDTO::getDirector)
                .contains("Rudolf Péter");}


    @Test
    void testUpdateMovie() {
        MovieDTO movie = template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris1", "Rudolf Péter"),
                MovieDTO.class);
        template.put("/api/movies/{id}", new UpdateMovieCommand("Fapuma", "Kapitány Iván"), movie.getId());
        List<MovieDTO> result = template.exchange(
                "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDTO>>(){}).getBody();
        assert result != null;
        assertEquals("Fapuma", result.get(0).getTitle());}


    @Test
    void testCreateMovieWithInvalidTitle(){
        Problem result = template.postForObject("/api/movies",
                new CreateMovieCommand("", "Rudolf Péter"),
                Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}


    @Test
    void testDeleteMovieById() {
        MovieDTO movie = template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris1", "Rudolf Péter"),
                MovieDTO.class);
        template.delete("/api/movies/{id}", movie.getId());
        List<MovieDTO> movies = template.exchange(
                "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDTO>>(){}).getBody();
        assertThat(movies).isEmpty();}


    @Test
    void deleteAllTheMovies() {
        template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris1", "Rudolf Péter"),
                MovieDTO.class);
        template.postForObject("/api/movies",
                new CreateMovieCommand("Üvegtigris2", "Rudolf Péter"),
                MovieDTO.class);
        template.delete("/api/movies");
        List<MovieDTO> movies = template.exchange(
                "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDTO>>(){}).getBody();
        assertThat(movies).isEmpty();}}
