package football.teams;

import football.players.CreatePlayerCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Operations on teams")
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing teams", description = "This option is for listing all the teams")
    public List<TeamDTO> listTeams() {return teamService.listTeams();}


//    @GetMapping("/{id}")
//    @Tag(name = "GET")
//    @Operation(summary = "Finding a team", description = "This option is for finding a team by ID")
//    public TeamDTO findTeamById(@PathVariable("id") long id) {
//        return teamService.findTeamById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a team", description = "This option is for saving a team")
    @ApiResponse(responseCode = "201", description = "New team has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a team")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO saveTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.saveTeam(command);}


    @PostMapping("/{id}/players")
    @Tag(name = "POST")
    @Operation(summary = "Adding a new player", description = "This option is for adding a new player to the existing team")
    public TeamDTO addNewPlayerToExistingTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.addNewPlayerToExistingTeam(id, command);}


//    @PutMapping("/{id}")
//    @Tag(name = "PUT")
//    @Operation(summary = "Updating a team", description = "This option is for updating a team's data")
//    @ApiResponse(responseCode = "200", description = "Team has been updated")
//    @ApiResponse(responseCode = "400", description = "Team update is not allowed")
//    public TeamDTO updateTeam(@PathVariable("id") long id, @Valid @RequestBody UpdateTeamCommand command) {
//        return teamService.updateTeam(id, command);}


    @PutMapping("/{id}/players")
    @Tag(name = "PUT")
    @Operation(summary = "Adding an existing player", description = "This option is for adding an existing player to the team")
    public TeamDTO addExistingPlayerToExistingTeam(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.addExistingPlayerToExistingTeam(id, command);}


//    @DeleteMapping("/{id}")
//    @Tag(name = "DELETE")
//    @Operation(summary = "Deleting a team", description = "This option is for deleting a team by ID")
//    @ApiResponse(responseCode = "204", description = "Team has been deleted")
//    @ApiResponse(responseCode = "404", description = "Team not found")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteTeamById(@PathVariable("id") long id) {teamService.deleteTeamById(id);}


//    @DeleteMapping
//    @Tag(name = "DELETE")
//    @Operation(summary = "Deleting all the teams", description = "This option is for deleting all the teams")
//    @ApiResponse(responseCode = "204", description = "Team have been deleted")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAllTheTeams(){teamService.deleteAllTheTeams();}
}
