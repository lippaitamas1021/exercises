package doggo.owner;

import doggo.NotFoundException;
import doggo.dog.*;
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
public class OwnerService {

    private DogRepository dogRepository;

    private OwnerRepository ownerRepository;

    private ModelMapper modelMapper;


    public List<OwnerDTO> listOwners(Optional<String> name) {
        Type targetListType = new TypeToken<List<OwnerDTO>>() {}.getType();
        return modelMapper.map(ownerRepository.findAll(), targetListType);}


    public OwnerDTO findOwnerById(int id) {
        return modelMapper.map(findOwner(id), OwnerDTO.class);}


    public Owner findOwner(int id) {
        return ownerRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Owner"));}


    public OwnerDTO saveOwner(CreateOwnerCommand command) {
        Owner owner = new Owner(command.getName());
        Owner result = ownerRepository.save(owner);
        return modelMapper.map(result, OwnerDTO.class);}


    @Transactional
    public OwnerDTO addNewDogToExistingOwner(int id, AddNewDogCommand command) {
        Owner owner = findOwner(id);
        Dog dog = new Dog(command.getName(), command.getBreed(), command.getAge(), command.getFavToy());
        owner.addDog(dog);
        dog.setOwner(owner);
        return modelMapper.map(owner, OwnerDTO.class);}


    @Transactional
    public OwnerDTO addExistingDogToExistingOwner(int id, AddExistingDogCommand command) {
        Dog dog = dogRepository.findById(command.getId()).orElseThrow(() -> new NotFoundException(command.getId(), "Dog"));
        Owner owner = findOwner(id);
        dog.setOwner(owner);
        owner.addDog(dog);
        return modelMapper.map(owner, OwnerDTO.class);}


    @Transactional
    public OwnerDTO updateOwner(int id, UpdateOwnerCommand command) {
        Owner owner = findOwner(id);
        owner.setName(command.getName());
        Owner result = ownerRepository.save(owner);
        return modelMapper.map(result, OwnerDTO.class);}


    public void deleteOwnerById(int id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Owner");}}


    public void deleteAllOwners() {
        ownerRepository.deleteAll();
    }}