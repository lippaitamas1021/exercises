package doggo.owner;

import doggo.dog.DogDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private int id;

    private String name;

    private List<DogDTO> dogs = new ArrayList<>();
}
