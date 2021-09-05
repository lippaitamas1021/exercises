package football.teams;

import football.players.Player;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Player> players = new ArrayList<>();


    public Team(String name) {
        this.name = name;}


    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);}}