package com.speedpersonapi.speedpersonapi.controller;

import com.speedpersonapi.speedpersonapi.dto.response.MessageResponseDTO;
import com.speedpersonapi.speedpersonapi.entity.Person;
import com.speedpersonapi.speedpersonapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody  Person person){
        return personService.createPerson(person);

    }

}
