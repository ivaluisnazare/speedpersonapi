package com.speedpersonapi.speedpersonapi.controller;

import com.speedpersonapi.speedpersonapi.dto.request.PersonDTO;
import com.speedpersonapi.speedpersonapi.dto.response.MessageResponseDTO;
import com.speedpersonapi.speedpersonapi.exception.PersonNotFoundException;
import com.speedpersonapi.speedpersonapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid PersonDTO personDTO) {
        return personService.create(personDTO);
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
    public MessageResponseDTO putPerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
       return personService.putPerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }

    @DeleteMapping
    public void delete(){
        personService.delete();
    }
}
