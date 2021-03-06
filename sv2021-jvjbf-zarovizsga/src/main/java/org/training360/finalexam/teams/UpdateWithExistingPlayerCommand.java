package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWithExistingPlayerCommand {

    @Schema(description = "Player's ID", example = "1")
    private long id;
}
