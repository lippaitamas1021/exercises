package doggo.dogs;

import doggo.dog.CreateDogCommand;
import doggo.dog.DogDTO;
import doggo.owner.Owner;
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
@Sql(statements = {"delete from dog"})
public class DogControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testAddNewDog(){
        DogDTO dog =
                template.postForObject("/api/dogs",
                        new CreateDogCommand("Ronnie", "BullDog", 3, "Ball"),
                        DogDTO.class);
        assertEquals("Ronnie",dog.getName());
        assertEquals(3,dog.getAge());
        assertEquals("BullDog", dog.getBreed());}


    @Test
    void testGetDogs(){
        template.postForObject("/api/dogs",
                new CreateDogCommand("Lili", "Mopps", 2, "Frisby"),
                DogDTO.class);
        template.postForObject("/api/dogs",
                new CreateDogCommand("Ronnie", "BullDog", 3, "Ball"),
                DogDTO.class);
        List<DogDTO> result = template.exchange(
                "/api/dogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DogDTO>>(){}).getBody();
        assertThat(result).extracting(DogDTO::getName)
                .containsExactly("Lili","Ronnie");}


    @Test
    void deleteDogById(){
        DogDTO result =template.postForObject("/api/dogs",
                new CreateDogCommand("Ronnie", "BullDog", 3, "Postman"),
                DogDTO.class);
        template.delete("/api/dogs/{id}", result.getId());
        List<DogDTO> players = template.exchange(
                "/api/dogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DogDTO>>(){}).getBody();
        assertThat(players).isEmpty();}


    @Test
    void testCreateDogWithInvalidName(){
        Problem result =
                template.postForObject("/api/dogs",
                        new CreateDogCommand("", "BullDog", 3, "Frisby"),
                        Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}}
