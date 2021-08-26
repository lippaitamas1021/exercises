package multimedia.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "music")
@Data
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
        this.genre = genre;}}