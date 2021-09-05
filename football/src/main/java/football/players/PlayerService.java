package football.players;

import football.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;


    public List<PlayerDTO> listPlayers() {
        Type targetType = new TypeToken<List<PlayerDTO>>(){}.getType();
        return modelMapper.map(playerRepository.findAll(), targetType);}


//    public PlayerDTO findPlayerById(long id) {
//        return modelMapper.map(findPlayer(id), PlayerDTO.class);}
    
    
//    public Player findPlayer(long id) {
//        return playerRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Player"));}


    public PlayerDTO saveNewPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);}


//    @Transactional
//    public PlayerDTO updatePlayer(long id, UpdatePlayerCommand command) {
//        Player player = findPlayer(id);
//        player.setName(command.getName());
//        return modelMapper.map(player, PlayerDTO.class);}


    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);}


//    public void deletePlayers() {playerRepository.deleteAll();}
}