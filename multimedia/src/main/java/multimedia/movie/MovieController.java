package multimedia.movie;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
@Tag(name = "Operations on movies")
public class MovieController {

    private MovieService movieService;


    @GetMapping
    public List<MovieDTO> listMovies() {
        return movieService.listMovies();}


    @GetMapping("/{id}")
    public MovieDTO findMovieById(@PathVariable("id") int id) {
        return movieService.findMovieById(id);}


    @PostMapping
    public MovieDTO saveMovie(@Valid @RequestBody CreateMovieCommand command) {
        return movieService.saveMovie(command);}


    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable("id") int id, @Valid @RequestBody UpdateMovieCommand command) {
        return movieService.updateMovie(id, command);}


    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable("id") int id) {
        movieService.deleteMovieById(id);}


    @DeleteMapping
    public void deleteAllTheMovies() {
        movieService.deleteAllTheMovies();}}
