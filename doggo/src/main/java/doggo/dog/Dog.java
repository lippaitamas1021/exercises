package doggo.dog;

import doggo.owner.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "dogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String breed;

    private int age;

    @ManyToOne(targetEntity = Owner.class)
    @JoinColumn(name = "dog_id")
    private Owner owner;

    @Column(name = "fav_toy")
    private String favToy;

    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }
}
