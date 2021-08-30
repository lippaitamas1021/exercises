package therapies.therapy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/therapies")
@Tag(name = "Operations on therapies")
@AllArgsConstructor
public class TherapyController {

    private TherapyService therapyService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing therapies", description = "This option is for listing all the therapies")
    public List<TherapyDto> listTherapies() {return therapyService.listTherapies();}


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a therapy by ID", description = "This option is for finding a therapy by ID")
    @ApiResponse(responseCode = "404", description = "Therapy not found")
    public TherapyDto findTherapyById(int id) {return therapyService.findTherapyById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a therapy", description = "This option is for saving a new therapy")
    @ApiResponse(responseCode = "201", description = "New therapy has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a therapy")
    @ResponseStatus(HttpStatus.CREATED)
    public TherapyDto saveTherapy(@Valid @RequestBody CreateTherapyCommand command) {
        return therapyService.saveTherapy(command);}


    @PostMapping("/{id}/participants")
    @Tag(name = "POST")
    @Operation(summary = "Adding a new participant", description = "This option is for adding a new participant to the existing therapy")
    public TherapyDto addNewParticipantToExistingTherapy(@PathVariable("id") int id, AddNewParticipantCommand command) {
        return therapyService.addNewParticipantToExistingTherapy(id, command);}


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating a therapy", description = "This option is for updating a therapy by ID")
    @ApiResponse(responseCode = "200", description = "Therapy has been updated")
    @ApiResponse(responseCode = "400", description = "Therapy update is not allowed")
    public TherapyDto updateTherapy(@PathVariable("id") int id, @Valid @RequestBody UpdateTherapyCommand command) {
        return therapyService.updateTherapy(id, command);}


    @PutMapping("/{id}/participants")
    @Tag(name = "PUT")
    @Operation(summary = "Adding an existing participant", description = "This option is for adding an existing participant to an existing therapy")
    public TherapyDto addExistingParticipantToExistingTherapy(@PathVariable("id") int id, @Valid @RequestBody AddExistingParticipantCommand command) {
        return therapyService.addExistingParticipantToExistingTherapy(id, command);}


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a therapy", description = "This option is for deleting one therapy by ID")
    @ApiResponse(responseCode = "204", description = "Therapy has been deleted")
    @ApiResponse(responseCode = "404", description = "Therapy not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTherapyById(@PathVariable("id") int id) {
        therapyService.deleteTherapyById(id);}


    @DeleteMapping
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting all therapies", description = "This option is for deleting all the therapies")
    @ApiResponse(responseCode = "204", description = "Therapies have been deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTherapies() {therapyService.deleteTherapies();}}