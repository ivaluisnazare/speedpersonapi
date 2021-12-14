package com.speedpersonapi.speedpersonapi.dto.mapper;

import com.speedpersonapi.speedpersonapi.dto.request.PersonDTO;
import com.speedpersonapi.speedpersonapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper{

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd/MM/yyyy")
    Person toModel(PersonDTO personDTO);
    PersonDTO toDTO(Person person);
}
