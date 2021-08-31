package doggo.owner;

import com.fasterxml.jackson.annotation.JsonBackReference;
import doggo.dog.Dog;
import lombok.*;
import org.hibernate.Hibernate;
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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Dog> dogs = new ArrayList<>();


    public Owner(String name) {
        this.name = name;}


    public void addDog(Dog dog) {
        dogs.add(dog);
        dog.setOwner(this);}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);}


    @Override
    public int hashCode() {
        return 1449427326;}}