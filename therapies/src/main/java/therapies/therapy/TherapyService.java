package therapies.therapy;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import therapies.NotFoundException;
import therapies.participant.Participant;
import therapies.participant.ParticipantRepository;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class TherapyService {

    private TherapyRepository therapyRepository;

    private ModelMapper modelMapper;

    private ParticipantRepository participantRepository;


    public List<TherapyDto> listTherapies() {
        Type targetType = new TypeToken<List<TherapyDto>>(){}.getType();
        return modelMapper.map(therapyRepository.findAll(), targetType);}


    public TherapyDto findTherapyById(int id) {
        return modelMapper.map(findTherapy(id), TherapyDto.class);}


    public Therapy findTherapy(int id) {
        return findT(id);}


    public TherapyDto saveTherapy(CreateTherapyCommand command) {
        Therapy therapy = new Therapy(command.getTherapist(), command.getPlace(), command.getDate());
        therapyRepository.save(therapy);
        return modelMapper.map(therapy, TherapyDto.class);}


    @Transactional
    public TherapyDto updateTherapy(int id, UpdateTherapyCommand command) {
        Therapy therapy = findT(id);
        therapy.setTherapist(command.getTherapist());
        Therapy result = therapyRepository.save(therapy);
        return modelMapper.map(result, TherapyDto.class);}


    @Transactional
    public TherapyDto addParticipantToTheTherapy(int id, AddParticipantCommand command) {
        Therapy therapy = findT(id);
        Participant participant = findP(command.getId());
        therapy.addParticipant(participant);
        participant.setTherapy(therapy);
        return modelMapper.map(therapy, TherapyDto.class);}


    public void deleteTherapyById(int id) {
        Therapy therapy = findT(id);
        therapyRepository.delete(therapy);}


    public void deleteTherapies() {
        therapyRepository.deleteAll();}


    private Therapy findT(int id) {
        return therapyRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Therapy"));}


    private Participant findP(int id) {
        return participantRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Participant"));}}