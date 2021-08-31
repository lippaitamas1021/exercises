package doggo.owner;

import doggo.dog.AddExistingDogCommand;
import doggo.dog.AddNewDogCommand;
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
@RequestMapping("/api/owners")
@Tag(name = "Operations on owners")
@AllArgsConstructor
public class OwnerController {

    private OwnerService ownerService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing owners", description = "This option is for listing all the owners")
    public List<OwnerDTO> listOwners(Optional<String> name) {
        return ownerService.listOwners(name);
    }


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding an owner", description = "This option is for finding an owner by ID")
    @ApiResponse(responseCode = "404", description = "Owner not found")
    public OwnerDTO findOwnerById(@PathVariable("id") int id) {
        return ownerService.findOwnerById(id);
    }


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving an owner", description = "This option is for saving an owner")
    @ApiResponse(responseCode = "201", description = "New owner has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving an owner")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDTO saveOwner(@Valid @RequestBody CreateOwnerCommand command) {
        return ownerService.saveOwner(command);}


    @PostMapping("/{id}/dogs")
    @Tag(name = "POST")
    @Operation(summary = "Adding a new dog", description = "This option is for adding a new dog to the owner")
    public OwnerDTO addNewDogToExistingOwner(@PathVariable("id") int id, @Valid @RequestBody AddNewDogCommand command) {
        return ownerService.addNewDogToExistingOwner(id, command);}


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating an owner", description = "This option is for updating an owner's data")
    @ApiResponse(responseCode = "200", description = "Owner has been updated")
    @ApiResponse(responseCode = "400", description = "Owner update is not allowed")
    public OwnerDTO updateOwner(@PathVariable("id") int id, @Valid @RequestBody UpdateOwnerCommand command) {
        return ownerService.updateOwner(id, command);}


    @PutMapping("/{id}/dogs")
    @Tag(name = "PUT")
    @Operation(summary = "Adding an existing dog", description = "This option is for adding an existing dog to an existing owner")
    public OwnerDTO addExistingDogToExistingOwner(@PathVariable("id") int id, @Valid @RequestBody AddExistingDogCommand command) {
        return ownerService.addExistingDogToExistingOwner(id, command);}


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting an owner", description = "This option is for deleting an owner by ID")
    @ApiResponse(responseCode = "204", description = "Owner has been deleted")
    @ApiResponse(responseCode = "404", description = "Owner not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwnerById(@PathVariable("id") int id) {
        ownerService.deleteOwnerById(id);
    }


    @DeleteMapping
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting all the owners", description = "This option is for deleting all the owners")
    @ApiResponse(responseCode = "204", description = "Owners have been deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllOwners() {
        ownerService.deleteAllOwners();
    }}