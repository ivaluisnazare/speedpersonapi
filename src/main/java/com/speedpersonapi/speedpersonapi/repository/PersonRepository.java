package com.speedpersonapi.speedpersonapi.repository;

import com.speedpersonapi.speedpersonapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
