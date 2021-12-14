package com.speedpersonapi.speedpersonapi.service;

import com.speedpersonapi.speedpersonapi.dto.request.PersonDTO;
import com.speedpersonapi.speedpersonapi.dto.response.MessageResponseDTO;
import com.speedpersonapi.speedpersonapi.entity.Person;
import com.speedpersonapi.speedpersonapi.dto.mapper.PersonMapper;
import com.speedpersonapi.speedpersonapi.exception.PersonNotFoundException;
import com.speedpersonapi.speedpersonapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll(){
        List<Person> allPerson = personRepository.findAll();
        return allPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO putPerson(Long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifyIfExists(id);
        Person personToUpdate = personRepository.getById(id);
            PersonDTO personDTOToUpdate = personMapper.toDTO(personToUpdate);
            personDTOToUpdate.setFirstName(personDTO.getFirstName());
            personDTOToUpdate.setLastName(personDTO.getLastName());
            personDTOToUpdate.setCpf(personDTO.getCpf());
            personDTOToUpdate.setBirthDate(personDTO.getBirthDate());
            personDTOToUpdate.setPhones(personDTO.getPhones());
            Person updatePerson = personMapper.toModel(personDTOToUpdate);
            Person update = personRepository.save(updatePerson);
            return createMessageResponse(update.getId(), "Update person with id ");
    }

    public void deleteById(Long id) throws PersonNotFoundException{
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public void delete(){
        personRepository.deleteAll();
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
