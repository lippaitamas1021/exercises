package doggo.dog;

import doggo.owner.CreateOwnerCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @Tag(name = "GET")
    @Operation(summary = "Listing dogs", description = "This option is for listing the dogs, optionally listing by breed")
    public List<DogDTO> listDogs(Optional<String> breed) {
        return dogService.listDogs(breed);
    }


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a dog", description = "This option is for finding a dog")
    @ApiResponse(responseCode = "404", description = "Dog not found")
    public DogDTO findDogById(@PathVariable("id") int id) {
        return dogService.findDogById(id);
    }


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a dog", description = "This option is for saving a dog")
    @ApiResponse(responseCode = "201", description = "New dog has been saved")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a dog")
    public DogDTO saveDog(@Valid @RequestBody CreateDogCommand command) {
        return dogService.saveDog(command);
    }


    @PostMapping("/{id}/owner")
    @Tag(name = "POST")
    @Operation(summary = "Adding owner", description = "This option is for adding an owner to the dog")
    public DogDTO addOwner(@PathVariable("id") int id,  @Valid @RequestBody CreateOwnerCommand command) {
        return dogService.addOwner(id, command); }


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating a dog", description = "This option is for updating the data of a dog")
    @ApiResponse(responseCode = "200", description = "Dog has been updated")
    @ApiResponse(responseCode = "400", description = "Dog update is not allowed")
    public DogDTO updateDog(@PathVariable("id") int id, @Valid @RequestBody UpdateDogCommand command) {
        return dogService.updateDog(id, command); }


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a dog", description = "This option is for deleting a dog by ID")
    @ApiResponse(responseCode = "204", description = "Dog has been deleted")
    @ApiResponse(responseCode = "404", description = "Dog not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDogById(@PathVariable("id") int id) {
        dogService.deleteDogById(id);
    }


    @DeleteMapping
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting dogs", description = "This option is for deleting all the dogs")
    @ApiResponse(responseCode = "204", description = "All te dogs have been deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllTheDogs() {
        dogService.deleteAllTheDogs();
    }}
