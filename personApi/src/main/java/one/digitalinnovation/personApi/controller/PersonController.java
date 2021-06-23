package one.digitalinnovation.personApi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personApi.dto.request.PersonDTO;
import one.digitalinnovation.personApi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personApi.exception.PersonNotFoundException;
import one.digitalinnovation.personApi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @GetMapping("/comands")
    public String Text(){
        return "<h1>Comandos</h2>" +
                "Criar Pessoa como parâmetros obrigatórios:" +
                "<ul><li>firstName</li><li>lastName</li><li>cpf</li><li>phones</li></ul>";
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){

        return personService.createPerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id,personDTO);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByYd(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

}
