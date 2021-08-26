package doggo.dog;

import doggo.owner.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDogCommand {

    @NotBlank(message = "Name of dog must be completed")
    @Schema(description = "Name of the dog", example = "Rex")
    private String name;


    @Schema(description = "Breed of the dog", example = "Mopps")
    private String breed;


    @Schema(description = "Age of the dog", example = "2")
    private int age;


    @Schema(description = "Favourit toys of the dog", example = "Ball")
    private String favToy;


    @Schema(description = "Owner of the dog", example = "Margot Robbie")
    private Owner owner;}
