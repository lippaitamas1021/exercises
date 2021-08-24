package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
@Tag(name = "Operations on teams")
public class TeamController {

    private TeamService teamService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing teams", description = "This option is for listing all the teams with all the players")
    public List<TeamDTO> listTeams() {return teamService.listTeams();}


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a player by ID", description = "This option is for finding a player by ID")
    @ApiResponse(responseCode = "404", description = "Team not found")
    public TeamDTO findTeamById(@PathVariable("id") long id) {return teamService.findTeamById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Creating a team", description = "This option is for creating a new team")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "New team has been created")
    @ApiResponse(responseCode = "400", description = "Validation exception while creating a new team")
    public TeamDTO createNewTeam(@Valid @RequestBody CreateTeamCommand command) {return teamService.createNewTeam(command);}


    @PostMapping("/{id}/players")
    @Tag(name = "POST")
    @Operation(summary = "Adding a player", description = "This option is for adding a player to the team")
    public TeamDTO addPlayerToTheTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.addPlayerToTheTeam(id, command);}


    @PutMapping("/{id}/players")
    public TeamDTO buyPlayer(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.buyPlayer(id, command);}
}
