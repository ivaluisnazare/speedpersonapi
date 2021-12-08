package com.speedpersonapi.speedpersonapi.controller;

import com.speedpersonapi.speedpersonapi.dto.response.MessageResponseDTO;
import com.speedpersonapi.speedpersonapi.entity.Person;
import com.speedpersonapi.speedpersonapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("created person with id " + savePerson.getId())
                .build();
    }
}
