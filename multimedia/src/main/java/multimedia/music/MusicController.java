package multimedia.music;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
    public List<Music> listMusic() {
        return musicService.listMusic();}


    @GetMapping("/{id}")
    public MusicDTO findMusicById(@PathVariable("id") int id) {
        return musicService.findMusicById(id);}


    @PostMapping
    public MusicDTO saveMusic(@Valid @RequestBody CreateMusicCommand command) {
        return musicService.saveMusic(command);}


    @PutMapping("/{id}")
    public MusicDTO updateMusic(@PathVariable("id") int id, @Valid @RequestBody UpdateMusicCommand command) {
        return musicService.updateMusic(id, command);}


    @DeleteMapping("/{id}")
    public void deleteMusicById(@PathVariable("id") int id) {
        musicService.deleteMusicById(id);}


    @DeleteMapping
    public void deleteAllTheMusic() {
        musicService.deleteAllTheMusic();}}