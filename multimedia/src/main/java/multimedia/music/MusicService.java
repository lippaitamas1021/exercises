package multimedia.music;

import lombok.AllArgsConstructor;
import multimedia.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class MusicService {

    private MusicRepository musicRepository;

    private ModelMapper modelMapper;


    public List<MusicDTO> listMusic() {
        Type targetListType = new TypeToken<List<MusicDTO>>(){}.getType();
        return modelMapper.map(musicRepository.findAll(), targetListType);}


    public MusicDTO findMusicById(int id) {
        return modelMapper.map(findMusic(id), MusicDTO.class);}


    public Music findMusic(int id) {
        return musicRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Music"));}


    public MusicDTO saveMusic(CreateMusicCommand command) {
        Music music = musicRepository.save(new Music(command.getPerformer(), command.getTitle(), command.getGenre()));
        return modelMapper.map(music, MusicDTO.class);}


    @Transactional
    public MusicDTO updateMusic(int id, UpdateMusicCommand command) {
        Music music = findMusic(id);
        music.setPerformer(command.getPerformer());
        music.setTitle(command.getTitle());
        music.setGenre(command.getGenre());
        return modelMapper.map(music, MusicDTO.class);}


    public void deleteMusicById(int id) {
        if(musicRepository.existsById(id)) {
            musicRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Music");}}


    public void deleteAllTheMusic() {
        musicRepository.deleteAll();}}