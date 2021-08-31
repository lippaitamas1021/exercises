package doggo.dog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewDogCommand {

    @NotBlank(message = "Name of the dog must be given")
    @Schema(description = "Name of the dog", example = "Ronnie")
    private String name;

    @Schema(description = "Breed of the dog", example = "BullDog")
    private String breed;

    @Schema(description = "Age of the dog", example = "2")
    private int age;

    @Schema(description = "Favourite toy of the dog", example = "Frisby")
    private String favToy;}