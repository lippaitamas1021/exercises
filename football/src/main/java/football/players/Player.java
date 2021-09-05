package football.players;

import football.teams.Team;
import lombok.*;
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
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated
    private PositionType position;

    @ManyToOne
    private Team team;


    public Player(String name, LocalDate birthDate, PositionType position) {
        this.name = name;
        this.dateOfBirth = birthDate;
        this.position = position;}


    public boolean hasNoTeam() {
        return team == null;}}