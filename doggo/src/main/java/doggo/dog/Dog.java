package doggo.dog;

import com.fasterxml.jackson.annotation.JsonBackReference;
import doggo.owner.Owner;
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
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String breed;

    private int age;

    @Column(name = "fav_toy")
    private String favToy;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Owner owner;

    public Dog(String name, String breed, int age, String favToy) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.favToy = favToy;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id);}


    @Override
    public int hashCode() {
        return 1216366848;}}