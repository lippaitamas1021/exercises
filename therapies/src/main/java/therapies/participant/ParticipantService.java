package therapies.participant;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapies.NotFoundException;
import therapies.therapy.TherapyRepository;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService {

    private ParticipantRepository participantRepository;

    private ModelMapper modelMapper;


    public List<ParticipantDto> listParticipants() {
        Type targetType = new TypeToken<List<ParticipantDto>>(){}.getType();
        return modelMapper.map(participantRepository.findAll(), targetType);}


    public ParticipantDto findParticipantById(int id) {
        return modelMapper.map(findParticipant(id), ParticipantDto.class);}


    public Participant findParticipant(int id) {
        return participantRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Participant"));}


    public ParticipantDto saveParticipant(CreateParticipantCommand command) {
        Participant participant = participantRepository.save(new Participant(command.getName()));
        return modelMapper.map(participant, ParticipantDto.class);}


    @Transactional
    public ParticipantDto updateParticipant(int id, UpdateParticipantCommand command) {
        Participant participant = findParticipant(id);
        participant.setName(command.getName());
        return modelMapper.map(participant, ParticipantDto.class);}


    public void deleteParticipantById(int id) {
        if (participantRepository.existsById(id)) {
            participantRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Participant");}}


    public void deleteAllTheParticipants() {
        participantRepository.deleteAll();}}