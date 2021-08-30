package therapies.participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import therapies.therapy.Therapy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {

    private int id;

    private String name;

    private Therapy therapy;}