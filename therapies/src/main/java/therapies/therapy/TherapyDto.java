package therapies.therapy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import therapies.participant.ParticipantDto;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapyDto {

    private int id;

    private String therapist;

    private String place;

    private String date;

    private List<ParticipantDto> participants = new ArrayList<>();}