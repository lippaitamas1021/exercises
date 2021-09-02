package multimedia.music;

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
@Sql(statements = "delete from music")
public class MusicControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateNewMusic(){
        MusicDTO result = template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "Hideaway", "Pop"),
                MusicDTO.class);
        assertEquals("Hideaway",result.getTitle());}


    @Test
    void testGetMusic(){
        template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "Hideaway", "Pop"),
                MusicDTO.class);
        template.postForObject("/api/music",
                new CreateMusicCommand("Adele", "Without you", "Pop"),
                MusicDTO.class);
        List<MusicDTO> result = template.exchange(
                "/api/music",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MusicDTO>>(){}).getBody();
        assertThat(result).extracting(MusicDTO::getGenre)
                .contains("Pop");}


    @Test
    void testUpdateMusic() {
        MusicDTO music1 = template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "Hideaway", "Pop"),
                MusicDTO.class);
        MusicDTO music2 = template.postForObject("/api/music",
                new CreateMusicCommand("Depeche Mode", "I feel you", "Pop"),
                MusicDTO.class);
        template.put("/api/music/{id}", new UpdateMusicCommand("Kiesza", "Over myself", "Pop"), music1.getId());
        template.put("/api/music/{id}", new UpdateMusicCommand("Depeche Mode", "Precious", "Pop"), music2.getId());
        List<MusicDTO> result = template.exchange(
                "/api/music",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MusicDTO>>(){}).getBody();
        assert result != null;
        assertEquals("Over myself", result.get(0).getTitle());
        assertEquals("Precious", result.get(1).getTitle());}


    @Test
    void testCreateMusicWithInvalidTitle(){
        Problem result = template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "", "Pop"),
                Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}


    @Test
    void testDeleteMusicById() {
        MusicDTO music = template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "Hideaway", "Pop"),
                MusicDTO.class);
        template.delete("/api/music/{id}", music.getId());
        List<MusicDTO> result = template.exchange(
                "/api/music",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MusicDTO>>(){}).getBody();
        assertThat(result).isEmpty();}


    @Test
    void deleteAllTheMusic() {
        template.postForObject("/api/music",
                new CreateMusicCommand("Kiesza", "Hideaway", "Pop"),
                MusicDTO.class);
        template.postForObject("/api/music",
                new CreateMusicCommand("Adele", "Without you", "Pop"),
                MusicDTO.class);
        template.delete("/api/music");
        List<MusicDTO> music = template.exchange(
                "/api/music",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MusicDTO>>(){}).getBody();
        assertThat(music).isEmpty();}}
