package doggo.dog;

import doggo.NotFoundException;
import doggo.owner.CreateOwnerCommand;
import doggo.owner.Owner;
import doggo.owner.OwnerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DogService {

    private DogRepository dogRepository;

    private ModelMapper modelMapper;

    private OwnerRepository ownerRepository;


    public List<DogDTO> listDogs(Optional<String> breed) {
        Type targetListType = new TypeToken<List<DogDTO>>(){}.getType();
        return modelMapper.map(dogRepository.findAll(), targetListType);}


    public DogDTO findDogById(int id) {
        return modelMapper.map(findDog(id), DogDTO.class);}


    public Dog findDog(int id) {
        return dogRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Dog"));}


    public DogDTO saveDog(CreateDogCommand command) {
        Dog dog = new Dog(command.getName(), command.getBreed(), command.getAge(), command.getFavToy());
        dogRepository.save(dog);
        return modelMapper.map(dog, DogDTO.class);}


    @Transactional
    public DogDTO addOwner(int id, CreateOwnerCommand command) {
        Owner owner = new Owner(command.getName());
        Dog dog = dogRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Dog"));
        ownerRepository.save(owner);
        dog.setOwner(owner);
        return modelMapper.map(dog, DogDTO.class);}


    @Transactional
    public DogDTO updateDog(int id, UpdateDogCommand command) {
        Dog dog = findDog(id);
        dog.setName(command.getName());
        dog.setAge(command.getAge());
        dog.setBreed(command.getBreed());
        dog.setFavToy(command.getFavToy());
        Dog result = dogRepository.save(dog);
        return modelMapper.map(result, DogDTO.class);}


    public void deleteDogById(int id) {
        if(dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Dog");}}


    public void deleteAllTheDogs() {
        dogRepository.deleteAll();
    }}
