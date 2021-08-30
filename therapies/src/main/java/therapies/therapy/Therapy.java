package therapies.therapy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import therapies.participant.Participant;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String therapist;

    private String place;

    private String date;

    @OneToMany(mappedBy = "therapy", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonBackReference
    private List<Participant> participants = new ArrayList<>();


    public Therapy(String therapist, String place, String date) {
        this.therapist = therapist;
        this.place = place;
        this.date = date;}


    public void addParticipant(Participant participant) {
        participant.setTherapy(this);
        participants.add(participant);}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Therapy therapy = (Therapy) o;
        return Objects.equals(id, therapy.id);}


    @Override
    public int hashCode() {
        return 793722864;}}