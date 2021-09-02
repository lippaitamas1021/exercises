package multimedia.music;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/music")
@AllArgsConstructor
@Tag(name = "Operations on music")
public class MusicController {

    private MusicService musicService;


    @GetMapping
    @Tag(name = "GET")
    @Operation(summary = "Listing the tracks", description = "This option is for listing all the tracks")
    public List<MusicDTO> listMusic() {return musicService.listMusic();}


    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Finding a track", description = "This option is for finding a given track")
    @ApiResponse(responseCode = "404", description = "Track not found")
    public MusicDTO findMusicById(@PathVariable("id") int id) {return musicService.findMusicById(id);}


    @PostMapping
    @Tag(name = "POST")
    @Operation(summary = "Saving a track", description = "This option is for saving a track")
    @ApiResponse(responseCode = "201", description = "New track has been saved")
    @ApiResponse(responseCode = "400", description = "Validation exception while saving a track")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicDTO saveMusic(@Valid @RequestBody CreateMusicCommand command) {
        return musicService.saveMusic(command);}


    @PutMapping("/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Updating a track", description = "This option is for updating a track by ID")
    @ApiResponse(responseCode = "200", description = "Track has been updated")
    @ApiResponse(responseCode = "400", description = "Track update is not allowed")
    public MusicDTO updateMusic(@PathVariable("id") int id, @Valid @RequestBody UpdateMusicCommand command) {
        return musicService.updateMusic(id, command);}


    @DeleteMapping("/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting a track", description = "This option is for deleting a track by ID")
    @ApiResponse(responseCode = "204", description = "Track has been deleted")
    @ApiResponse(responseCode = "404", description = "Track not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusicById(@PathVariable("id") int id) {
        musicService.deleteMusicById(id);}


    @DeleteMapping
    @Tag(name = "DELETE")
    @Operation(summary = "Deleting all the tracks", description = "This option is for deleting all the tracks")
    @ApiResponse(responseCode = "204", description = "Tracks have been deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllTheMusic() {
        musicService.deleteAllTheMusic();}}