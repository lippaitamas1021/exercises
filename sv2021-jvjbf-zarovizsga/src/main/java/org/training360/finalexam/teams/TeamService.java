package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.training360.finalexam.EntityNotFoundException;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;


    public List<TeamDTO> listTeams() {
        List<Team> teams = teamRepository.findAll();
        Type targetListType = new TypeToken<List<TeamDTO>>(){}.getType();
        return modelMapper.map(teams, targetListType);}


    public TeamDTO findTeamById(long id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, "Team"));
        return modelMapper.map(team, TeamDTO.class);}


    public TeamDTO createNewTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);}


    @Transactional
    public TeamDTO addPlayerToTheTeam(long id, CreatePlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, "Team"));
        Player player = new Player(
                command.getName(),
                command.getDateOfBirth(),
                command.getPosition());
        team.addNewPlayerToTheTeam(player);
        return modelMapper.map(team, TeamDTO.class);}


    public TeamDTO buyPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, "Team"));
        Player player = playerRepository.findById(command.getId()).orElseThrow(()-> new EntityNotFoundException(command.getId(), "Player"));
        if(player.hasNoTeam() && hasEmptyPosition(team, player)) {
            team.addNewPlayerToTheTeam(player);}
        return modelMapper.map(team, TeamDTO.class);}

    private boolean hasEmptyPosition(Team team, Player player) {
        return team.getPlayers().stream().filter(p -> p.getPosition() == player.getPosition()).collect(Collectors.toList()).size() < 2;}
}
