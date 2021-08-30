package therapies.participant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from participant"})
public class ParticipantControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testGetParticipants(){
        template.postForObject("/api/participants",
                new CreateParticipantCommand("Mila Kunis"), ParticipantDto.class);
        template.postForObject("/api/participants",
                new CreateParticipantCommand("Margot Robbie"), ParticipantDto.class);
        List<ParticipantDto> result = template.exchange(
                "/api/participants",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ParticipantDto>>(){}).getBody();
        assertThat(result).extracting(ParticipantDto::getName)
                .containsExactly("Mila Kunis","Margot Robbie");}


    @Test
    void testGetParticipantById(){
        ParticipantDto participant = template.postForObject("/api/participants",
                new CreateParticipantCommand("Mila Kunis"), ParticipantDto.class);
        List<ParticipantDto> result = template.exchange(
                "/api/participants",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ParticipantDto>>(){}).getBody();
        assertThat(result).extracting(ParticipantDto::getId).containsExactly(participant.getId());}


    @Test
    void testSaveNewParticipant(){
        ParticipantDto result = template.postForObject("/api/participants",
                new CreateParticipantCommand("Mila Kunis"), ParticipantDto.class);
        assertEquals("Mila Kunis",result.getName());}


    @Test
    void testCreateParticipantWithInvalidName(){
        Problem result =
                template.postForObject("/api/participants",
                        new CreateParticipantCommand(""), Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}


    @Test
    void deleteParticipantById(){
        ParticipantDto result = template.postForObject("/api/participants",
                new CreateParticipantCommand("Mila Kunis"), ParticipantDto.class);
        template.delete("/api/participants/{id}", result.getId());
        List<ParticipantDto> players = template.exchange(
                "/api/participants",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ParticipantDto>>(){}).getBody();
        assertThat(players).isEmpty();}}