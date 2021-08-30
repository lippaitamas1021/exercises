package therapies.participant;

import lombok.*;
import org.hibernate.Hibernate;
import therapies.therapy.Therapy;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Therapy therapy;


    public Participant(String name) {
        this.name = name;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Participant that = (Participant) o;
        return Objects.equals(id, that.id);}


    @Override
    public int hashCode() {
        return 347252158;}}