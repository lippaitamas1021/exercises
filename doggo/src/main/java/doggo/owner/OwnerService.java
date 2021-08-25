package doggo.owner;

import doggo.NotFoundException;
import doggo.dog.CreateDogCommand;
import doggo.dog.Dog;
import doggo.dog.DogRepository;
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
        Type targetListType = new TypeToken<List<OwnerDTO>>() {
        }.getType();
        return modelMapper.map(ownerRepository.findAll(), targetListType);
    }

    public OwnerDTO findOwnerById(int id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(owner, OwnerDTO.class);
    }

    public OwnerDTO createOwner(CreateOwnerCommand command) {
        Owner owner = new Owner(command.getName());
        ownerRepository.save(owner);
        return modelMapper.map(owner, OwnerDTO.class);
    }

    @Transactional
    public OwnerDTO addNewDogToOwner(int id, CreateDogCommand command) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Dog dog = new Dog(command.getName(), command.getBreed(), command.getAge());
        owner.addDog(dog);
        dog.setOwner(owner);
        return modelMapper.map(owner, OwnerDTO.class);
    }

    @Transactional
    public OwnerDTO addExistingDogToOwner(int id, UpdateWithExistingDogCommand command) {
        Dog dog = dogRepository.findById(command.getId()).orElseThrow(() -> new NotFoundException(command.getId()));
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        dog.setOwner(owner);
        owner.addDog(dog);
        return modelMapper.map(owner, OwnerDTO.class);
    }

    public void deleteOwnerById(int id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new NotFoundException(id);
        }
    }

    public void deleteAllOwners() {
        ownerRepository.deleteAll();
    }
}
