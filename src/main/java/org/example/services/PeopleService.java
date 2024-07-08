package org.example.services;

import org.example.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.example.dto.PersonDTO;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<PersonDTO> getAllPeople() {
        return peopleRepository.findAll();

    }

}
