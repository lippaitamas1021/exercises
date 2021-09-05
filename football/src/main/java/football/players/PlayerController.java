package football.players;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Operations on players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing players", description = "This option is for listing all the players")
    public List<PlayerDTO> listPlayers() {return playerService.listPlayers();}


//    @GetMapping("/{id}")
//    @Tag(name = "GET")
//    @Operation(summary = "Finding a player", description = "This option is for finding a player by ID")
//    @ApiResponse(responseCode = "404", description = "Player not found")
//    public PlayerDTO findPlayerById(@PathVariable("id") long id) {return playerService.findPlayerById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a new player", description = "This option is for saving a new player")
    @ApiResponse(responseCode = "201", description = "New player has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a new player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO saveNewPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.saveNewPlayer(command);}


//    @PutMapping("/{id}")
//    @Tag(name = "PUT")
//    @Operation(summary = "Updating an existing player", description = "This option is for updating a player's data")
//    @ApiResponse(responseCode = "200", description = "Player has been updated")
//    @ApiResponse(responseCode = "400", description = "Updating is not allowed")
//    public PlayerDTO updatePlayer(@PathVariable("id") long id, @Valid @RequestBody UpdatePlayerCommand command) {
//        return playerService.updatePlayer(id, command);}


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a player", description = "This option is for deleting a player by ID")
    @ApiResponse(responseCode = "204", description = "Player has been deleted")
    @ApiResponse(responseCode = "404", description = "Player not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayerById(@PathVariable("id") long id) {playerService.deletePlayerById(id);}


//    @DeleteMapping
//    @Tag(name = "DELETE")
//    @Operation(summary = "Deleting all the players", description = "This option is for deleting all the players")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePlayers() {playerService.deletePlayers();}
}