package multimedia.movie;

import lombok.AllArgsConstructor;
import multimedia.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    
    private MovieRepository movieRepository;

    private ModelMapper modelMapper;


    public List<MovieDTO> listMovies() {
        Type targetListType = new TypeToken<List<MovieDTO>>(){}.getType();
        return modelMapper.map(movieRepository.findAll(), targetListType);}


    public MovieDTO findMovieById(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Movie"));
        return modelMapper.map(movie, MovieDTO.class);}


    public MovieDTO saveMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle(), command.getDirector(), command.getStudio());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);}


    @Transactional
    public MovieDTO updateMovie(int id, UpdateMovieCommand command) {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Movie"));
        movie.setTitle(command.getTitle());
        movie.setDirector(command.getDirector());
        movie.setStudio(command.getStudio());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);}


    public void deleteMovieById(int id) {
        if(movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Movie");}}


    public void deleteAllTheMovies() {
        movieRepository.deleteAll();}}