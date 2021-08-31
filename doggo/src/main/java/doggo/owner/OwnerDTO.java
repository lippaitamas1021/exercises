package doggo.owner;

import com.fasterxml.jackson.annotation.JsonBackReference;
import doggo.dog.DogDTO;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private int id;

    private String name;

    @JsonBackReference
    private List<DogDTO> dogs;}