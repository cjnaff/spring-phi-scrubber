package org.example.repository;

import org.example.dto.PersonDTO;
import org.springframework.data.repository.ListCrudRepository;

public interface PeopleRepository extends ListCrudRepository<PersonDTO, Long> {

}
