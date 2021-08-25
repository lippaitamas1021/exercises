package doggo.dog;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dogs")
@AllArgsConstructor
@Tag(name = "Operations on dogs")
public class DogController {

    private DogService dogService;

    @GetMapping
    public List<DogDTO> listDogs(Optional<String> breed) {
        return dogService.listDogs(breed);
    }

    @GetMapping("/{id}")
    public DogDTO findDogById(@PathVariable("id") int id) {
        return dogService.findDogById(id);
    }

    @PostMapping
    public DogDTO saveDog(@Valid @RequestBody CreateDogCommand command) {
        return dogService.saveDog(command);
    }

    @DeleteMapping("/{id}")
    public void deleteDogById(@PathVariable("id") int id) {
        dogService.deleteDogById(id);
    }

    @DeleteMapping
    public void deleteAllTheDogs() {
        dogService.deleteAllTheDogs();
    }
}
