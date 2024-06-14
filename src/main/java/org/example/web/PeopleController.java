package org.example.web;

import org.example.dto.PersonDTO;
import org.example.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping(path="/people")
    public List<PersonDTO> getPeople() { 
		return peopleRepository.findAll(); 
	}

    @GetMapping(path="/people/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long id) {
        return peopleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
@PostMapping(path = "/people")
public ResponseEntity<PersonDTO> createPerson(@Validated @RequestBody PersonDTO personDTO) {
	PersonDTO createdPerson = peopleRepository.save(personDTO);
	return ResponseEntity.created(
			URI.create("/people/" + createdPerson.getId())
			).body(createdPerson);
	}

}
