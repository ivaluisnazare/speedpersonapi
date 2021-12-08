package com.speedpersonapi.speedpersonapi.service;

import com.speedpersonapi.speedpersonapi.dto.response.MessageResponseDTO;
import com.speedpersonapi.speedpersonapi.entity.Person;
import com.speedpersonapi.speedpersonapi.repository.PersonRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("created person with id " + savePerson.getId())
                .build();
    }
}
