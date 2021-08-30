package therapies.therapy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import therapies.participant.CreateParticipantCommand;
import therapies.participant.ParticipantDto;
import java.net.URI;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from participant","delete from therapy"})
public class TherapyControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateNewTherapy(){
        TherapyDto result = template.postForObject("/api/therapies",
                new CreateTherapyCommand("Mila Kunis", "Arena", "20210901"),
                TherapyDto.class);
        assertEquals("Arena",result.getPlace());
        assertEquals("Mila Kunis",result.getTherapist());}


    @Test
    void testGetTherapies(){
        template.postForObject("/api/therapies",
                new CreateTherapyCommand("Mila Kunis", "Arena", "20210901"),
                TherapyDto.class);
        template.postForObject("/api/therapies",
                new CreateTherapyCommand("Anne Hathaway", "Sport center", "20210902"),
                TherapyDto.class);
        List<TherapyDto> result = template.exchange(
                "/api/therapies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TherapyDto>>(){}).getBody();
        assertThat(result).extracting(TherapyDto::getPlace)
                .containsExactly("Arena", "Sport center");}


     @Test
    void testAddNewParticipantToExistingTherapy(){
         TherapyDto therapy = template.postForObject("/api/therapies",
                 new CreateTherapyCommand("Anne Hathaway", "Sport center", "20210902"),
                 TherapyDto.class);
         template.postForObject("/api/therapies/{id}/participants", new AddNewParticipantCommand("Jennifer Aniston"),
                 TherapyDto.class, therapy.getId());
         List<TherapyDto> result = template.exchange(
                 "/api/therapies",
                 HttpMethod.GET,
                 null,
                 new ParameterizedTypeReference<List<TherapyDto>>(){}).getBody();
         assert result != null;
         assertEquals(1, result.stream().map(TherapyDto::getParticipants).count());}


    @Test
    void testAddExistingParticipantToExistingTherapy(){
        TherapyDto therapy = template.postForObject("/api/therapies",
                new CreateTherapyCommand("Anne Hathaway", "Sport center", "20210902"),
                TherapyDto.class);
        ParticipantDto participant = template.postForObject("/api/participants",
                new CreateParticipantCommand("Jennifer Aniston"), ParticipantDto.class);
        template.put("/api/therapies/{id}/participants", new AddExistingParticipantCommand(participant.getId()), therapy.getId());
        List<TherapyDto> result = template.exchange(
                "/api/therapies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TherapyDto>>(){}).getBody();
        assert result != null;
        assertThat(result.get(0).getParticipants()).extracting(ParticipantDto::getName)
                .containsExactly("Jennifer Aniston");}


    @Test
    void testUpdateTherapy(){
        TherapyDto therapy = template.postForObject("/api/therapies",
                new CreateTherapyCommand("Anne Hathaway", "Sport center", "20210902"),
                TherapyDto.class);
        template.put("/api/therapies/{id}", new UpdateTherapyCommand("Mila Kunis"), therapy.getId());
        List<TherapyDto> result = template.exchange(
                "/api/therapies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TherapyDto>>() {}).getBody();
        assert result != null;
        assertThat(result.stream()).extracting(TherapyDto::getTherapist).containsExactly("Mila Kunis");}


    @Test
    void testAddNewParticipantToNotExistingTherapy(){
        int wrongId = 6666;
        Problem result = template.postForObject("/api/therapies/{id}/participants",
                new AddNewParticipantCommand("Margot Robbie"), Problem.class, wrongId);
        assertEquals(URI.create("Therapy/not-found"),result.getType());
        assertEquals(Status.NOT_FOUND,result.getStatus());}


    @Test
    void testCreateTherapyWithInvalidTherapist(){
        Problem result = template.postForObject("/api/therapies",
                new CreateTherapyCommand("", "Arena", "20210901"), Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}}