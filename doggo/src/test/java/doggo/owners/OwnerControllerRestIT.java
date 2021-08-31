package doggo.owners;

import doggo.dog.AddNewDogCommand;
import doggo.dog.CreateDogCommand;
import doggo.dog.DogDTO;
import doggo.owner.CreateOwnerCommand;
import doggo.owner.OwnerDTO;
import doggo.owner.UpdateWithExistingDogCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.net.URI;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from dog", "delete from owner"})
public class OwnerControllerRestIT {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateNewOwner(){
        OwnerDTO result = template.postForObject("/api/owners",
                new CreateOwnerCommand("Margot Robbie"),
                OwnerDTO.class);
        assertEquals("Margot Robbie",result.getName());}


    @Test
    void testGetOwners(){
        template.postForObject("/api/owners",
                new CreateOwnerCommand("Mila Kunis"),
                OwnerDTO.class);
        template.postForObject("/api/owners",
                new CreateOwnerCommand("Margot Robbie"),
                OwnerDTO.class);
        List<OwnerDTO> result = template.exchange(
                "/api/owners",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OwnerDTO>>(){}).getBody();
        assertThat(result).extracting(OwnerDTO::getName)
                .contains("Mila Kunis","Margot Robbie");}


    @Test
    void testAddNewDogToExistingOwner(){
        OwnerDTO owner =
                template.postForObject("/api/owners",
                        new CreateOwnerCommand("Mila Kunis"),
                        OwnerDTO.class);
        OwnerDTO ownerWithDog = template.postForObject("/api/owners/{id}/dogs",
                new AddNewDogCommand("Lili", "Mopps", 2, "Frisby"), OwnerDTO.class, owner.getId());
        assertThat(ownerWithDog.getDogs()).extracting(DogDTO::getName)
                .contains("Lili");}


    @Test
    void testAddExistingDogWithOwner(){
        OwnerDTO owner =
                template.postForObject("/api/owners",
                        new CreateOwnerCommand("Margot Robbie"),
                        OwnerDTO.class);
        DogDTO dog =
                template.postForObject("/api/dogs",
                        new CreateDogCommand("Lili","Mopps", 2, "Ball"),
                        DogDTO.class);
        template.put("/api/owners/{id}/dogs", new UpdateWithExistingDogCommand(dog.getId()), owner.getId());
        List<OwnerDTO> result = template.exchange(
                "/api/owners",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OwnerDTO>>() {}).getBody();
        assert result != null;
        assertThat(result.get(0).getDogs()).extracting(DogDTO::getName)
                .contains("Lili");}


    @Test
    void testAddDogToNotExistingOwner(){
        int id = 212;
        Problem result = template.postForObject("/api/owners/{id}/dogs",
                new CreateDogCommand("Lili", "Mopps", 2, "Frisby"),
                Problem.class,
                id);
        assertEquals(URI.create("Owner/not-found"),result.getType());
        assertEquals(Status.NOT_FOUND,result.getStatus());}


    @Test
    void testCreateOwnerWithInvalidName(){
        Problem result =
                template.postForObject("/api/owners",
                        new CreateOwnerCommand(""),
                        Problem.class);
        assertEquals(Status.BAD_REQUEST,result.getStatus());}}
