package doggo.dog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDogCommand {

    @NotBlank(message = "Name of the dog must be completed")
    private String name;

    private String breed;

    private int age;
}
