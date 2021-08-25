package doggo.owner;

import doggo.dog.CreateDogCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
@Tag(name = "Operations on owners")
@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;

    @GetMapping
    public List<OwnerDTO> listOwners(Optional<String> name) {
        return ownerService.listOwners(name);
    }

    @GetMapping("/{id}")
    public OwnerDTO findOwnerById(@PathVariable("id") int id) {
        return ownerService.findOwnerById(id);
    }

    @PostMapping
    public OwnerDTO createOwner(@Valid @RequestBody CreateOwnerCommand command) {
        return ownerService.createOwner(command);
    }

    @PostMapping("/{id}/dogs")
    public OwnerDTO addNewDogToOwner(@PathVariable("id") int id, @Valid @RequestBody CreateDogCommand command) {
        return ownerService.addNewDogToOwner(id, command);
    }

    @PutMapping("/{id}/dogs")
    public OwnerDTO addExistingDogToOwner(@PathVariable("id") int id, @Valid @RequestBody UpdateWithExistingDogCommand command) {
        return ownerService.addExistingDogToOwner(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteOwnerById(@PathVariable("id") int id) {
        ownerService.deleteOwnerById(id);
    }

    @DeleteMapping
    public void deleteAllOwners() {
        ownerService.deleteAllOwners();
    }
}
