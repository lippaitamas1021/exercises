package multimedia.music;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String performer;

    private String title;

    private String genre;


    public Music(String performer, String title, String genre) {
        this.performer = performer;
        this.title = title;
        this.genre = genre;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Music music = (Music) o;

        return Objects.equals(id, music.id);}


    @Override
    public int hashCode() {
        return 1086698890;}}