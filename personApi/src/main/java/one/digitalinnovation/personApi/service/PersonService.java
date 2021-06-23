package one.digitalinnovation.personApi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personApi.dto.request.PersonDTO;
import one.digitalinnovation.personApi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personApi.entity.Person;
import one.digitalinnovation.personApi.exception.PersonNotFoundException;
import one.digitalinnovation.personApi.mapper.PersonMapper;
import one.digitalinnovation.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper=PersonMapper.INSTANCE;


    @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave=personMapper.toModel(personDTO);


        Person personSaved=personRepository.save(personToSave);
        return CreateMessageResponse(personSaved.getId(),"Create Person with id ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople=personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {

        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }



    public void delete(Long id) throws PersonNotFoundException {

        verifyIfExists(id);

        personRepository.deleteById(id);

    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate=personMapper.toModel(personDTO);


        Person updatedPerson=personRepository.save(personToUpdate);
        return CreateMessageResponse(updatedPerson.getId(),"Updated where Id is ");
    }

    private MessageResponseDTO CreateMessageResponse(Long id,String message) {
        return MessageResponseDTO.builder().
                message(message+id).
                build();
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(()->new PersonNotFoundException(id));
    }
}
