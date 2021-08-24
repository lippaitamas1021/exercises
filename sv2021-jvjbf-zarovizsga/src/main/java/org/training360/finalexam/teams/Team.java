package org.training360.finalexam.teams;

import lombok.*;
import org.training360.finalexam.players.Player;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "teams")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Player> players = new ArrayList<>();


    public Team(String name) {
        this.name = name;}


    public void addNewPlayerToTheTeam(Player player) {
        player.setTeam(this);
        players.add(player);}


    public int numberOfPlayersAtOnePosition(Player player) {
        int count = 0;
        for(Player p : players) {
            if (p.getPosition() == player.getPosition()) {
                count++;}}
        return count;}
}