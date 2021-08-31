package doggo.dog;

import doggo.owner.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDTO {

    private int id;

    private String name;

    private String breed;

    private int age;

    private String favToy;

    private Owner owner;}
