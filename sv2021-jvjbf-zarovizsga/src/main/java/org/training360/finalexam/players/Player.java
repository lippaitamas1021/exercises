package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.teams.Team;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated
    private PositionType position;

    @ManyToOne
    private Team team;

    public Player(String name, LocalDate dateOfBirth, PositionType position) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.position = position;}

    public boolean hasNoTeam() {
        return team == null;}
}