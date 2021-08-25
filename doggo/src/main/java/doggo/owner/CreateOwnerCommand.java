package doggo.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerCommand {

    @NotBlank(message = "Owner's name must be completed")
    private String name;
}
