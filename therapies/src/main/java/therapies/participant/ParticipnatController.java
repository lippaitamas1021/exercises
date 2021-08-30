package therapies.participant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/participants")
@Tag(name = "Operations on participants")
@AllArgsConstructor
public class ParticipnatController {

    private ParticipantService participantService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing participants", description = "This option is for listing all the participants")
    public List<ParticipantDto> listParticipants() {
        return participantService.listParticipants();}


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a participant by ID", description = "This option is for finding a participant by ID")
    @ApiResponse(responseCode = "404", description = "Participant not found")
    public ParticipantDto findParticipantById(@PathVariable("id") int id) {
        return participantService.findParticipantById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a participant", description = "This option is for saving a new participant")
    @ApiResponse(responseCode = "201", description = "New participant has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a participant")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantDto saveParticipant(@Valid @RequestBody CreateParticipantCommand command) {
        return participantService.saveParticipant(command);}


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating a participant", description = "This option is for updating a participant by ID")
    @ApiResponse(responseCode = "200", description = "Participant has been updated")
    @ApiResponse(responseCode = "400", description = "Participant update is not allowed")
    public ParticipantDto updateParticipant(@PathVariable("id") int id, @Valid @RequestBody UpdateParticipantCommand command) {
        return participantService.updateParticipant(id, command);}


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a participant", description = "This option is for deleting one participant by ID")
    @ApiResponse(responseCode = "204", description = "Participant has been deleted")
    @ApiResponse(responseCode = "404", description = "Participant not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipantById(@PathVariable("id") int id) {
        participantService.deleteParticipantById(id);}


    @DeleteMapping
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting all participants", description = "This option is for deleting all the participants")
    @ApiResponse(responseCode = "204", description = "Participants have been deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllTheParticipants() {
        participantService.deleteAllTheParticipants();}}