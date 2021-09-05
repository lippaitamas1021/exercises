package football.teams;

import football.NotFoundException;
import football.players.CreatePlayerCommand;
import football.players.Player;
import football.players.PlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    private ModelMapper modelMapper;

    private PlayerRepository playerRepository;


    public List<TeamDTO> listTeams() {
        Type targetType = new TypeToken<List<TeamDTO>>(){}.getType();
        return modelMapper.map(teamRepository.findAll(), targetType);}


//    public TeamDTO findTeamById(long id) {
//        return modelMapper.map(findTeam(id), TeamDTO.class);}


    public Team findTeam(long id) {
        return teamRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Team"));}


    public TeamDTO saveTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);}


    @Transactional
    public TeamDTO addNewPlayerToExistingTeam(long id, CreatePlayerCommand command) {
        Team team = findTeam(id);
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        team.addPlayer(player);
        return modelMapper.map(team, TeamDTO.class);}


//    @Transactional
//    public TeamDTO updateTeam(long id, UpdateTeamCommand command) {
//        Team team = findTeam(id);
//        team.setName(command.getName());
//        Team result = teamRepository.save(team);
//        return modelMapper.map(result, TeamDTO.class);}


    @Transactional
    public TeamDTO addExistingPlayerToExistingTeam(long id, UpdateWithExistingPlayerCommand command) {
        Team team = findTeam(id);
        Player player = playerRepository.findById(command.getId()).orElseThrow(()-> new NotFoundException(command.getId(), "Player"));
        if (player.hasNoTeam() && hasEmptyPosition(team, player)) {
            team.addPlayer(player);}
        return modelMapper.map(team, TeamDTO.class);}



    private boolean hasEmptyPosition(Team team, Player player) {
        return team.getPlayers().stream()
                .filter(p -> p.getPosition() == player.getPosition()).count() < 2;}


//    public void deleteTeamById(long id) {
//        Team team = findTeam(id);
//        teamRepository.delete(team);}


//    public void deleteAllTheTeams() {
//        teamRepository.deleteAll();}
}