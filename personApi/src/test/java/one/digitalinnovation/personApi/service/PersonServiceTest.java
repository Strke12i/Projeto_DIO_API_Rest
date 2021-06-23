package one.digitalinnovation.personApi.service;


import one.digitalinnovation.personApi.dto.request.PersonDTO;
import one.digitalinnovation.personApi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personApi.entity.Person;
import one.digitalinnovation.personApi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personApi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personApi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    @Test
    void TestGivenPersonDTOThenReturnSavedMessage (){
        PersonDTO personDTO=createFakeDTO();
        Person expectedSavedPerson=createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expected_message= createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO success_message =personService.createPerson(personDTO);

        assertEquals(expected_message, success_message);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Create Person with id " + id)
                .build();
    }
}
