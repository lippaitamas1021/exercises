package doggo.dog;

import doggo.NotFoundException;
import doggo.owner.CreateOwnerCommand;
import doggo.owner.OwnerDTO;
import doggo.owner.UpdateWithExistingDogCommand;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DogService {

    private DogRepository dogRepository;

    private ModelMapper modelMapper;


    public List<DogDTO> listDogs(Optional<String> breed) {
        Type targetListType = new TypeToken<List<DogDTO>>(){}.getType();
        return modelMapper.map(dogRepository.findAll(), targetListType);
    }

    public DogDTO findDogById(int id) {
        Dog dog = dogRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return modelMapper.map(dog, DogDTO.class);
    }

//    public List<DogDTO> listDogsByBreed(String breed) {
//        Type targetListType = new TypeToken<List<DogDTO>>(){}.getType();
//        List<Dog> result = dogRepository.findAll().stream().filter(d -> d.getBreed().equals(breed)).collect(Collectors.toList());
//        return modelMapper.map(result, targetListType);
//    }

    public DogDTO saveDog(CreateDogCommand command) {
        Dog dog = new Dog(command.getName(), command.getBreed(), command.getAge());
        dogRepository.save(dog);
        return modelMapper.map(dog, DogDTO.class);
    }

    public void deleteDogById(int id) {
        dogRepository.deleteById(id);
    }

    public void deleteAllTheDogs() {
        dogRepository.deleteAll();
    }
}
