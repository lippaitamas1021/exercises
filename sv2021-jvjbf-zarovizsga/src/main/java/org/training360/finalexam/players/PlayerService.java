package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.training360.finalexam.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;


    public List<PlayerDTO> listPlayers(Optional<String> name) {
        List<Player> players = playerRepository.findAll();
        Type targetListType = new TypeToken<List<PlayerDTO>>(){}.getType();
        return modelMapper.map(players, targetListType);}


    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getDateOfBirth(), command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);}


    public void deletePlayer(long id) {
        if(!playerRepository.existsById(id)) {
            throw new EntityNotFoundException(id, "Player");}
        playerRepository.deleteById(id);}
}
