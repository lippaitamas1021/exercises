package doggo.owner;

import doggo.dog.Dog;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Dog> dogs = new ArrayList<>();

    public Owner(String name) {
        this.name = name;}

    public void addDog(Dog dog) {
        dogs.add(dog);
        dog.setOwner(this);}
}
