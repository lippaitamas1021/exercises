package org.training360.finalexam.players;

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
@RequestMapping("/api/players")
@Tag(name = "Operations on players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing players", description = "This option is for listing all the players")
    public List<PlayerDTO> listPlayers(@RequestParam Optional<String> name) {return playerService.listPlayers(name);}


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Tag(name = "POST")
    @Operation(summary = "Adding a new player", description = "This option is for adding a new player to the league")
    @ApiResponse(responseCode = "201", description = "New player has been added to the league")
    @ApiResponse(responseCode = "400", description = "Validation exception while adding a new player")
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {return playerService.createPlayer(command);}


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a player", description = "This option is for deleting a player by ID")
    @ApiResponse(responseCode = "204", description = "Player has been deleted")
    @ApiResponse(responseCode = "404", description = "Player not found")
    public void deletePlayer(@PathVariable("id") long id) {playerService.deletePlayer(id);}
}
