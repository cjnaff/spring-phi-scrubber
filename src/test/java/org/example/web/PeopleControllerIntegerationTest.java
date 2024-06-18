package org.example.web;

import org.example.dto.PersonDTO;
import org.example.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PeopleControllerIntegerationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int localPort;
//    @Autowired
//    private PeopleRepository peopleRepository;

    @Test
    void shouldGetPeople() {
        // when
        ResponseEntity<List<PersonDTO>> response = getAllPeople();
        // then
        System.out.println("Got List");
    }


    @Test
    void shouldGetPersonById() {
        //given
        // when
        // then
    }

    @Test
    void shouldCreatePerson() {
        //given
        var personToCreate = new PersonDTO("Baily", "Peterson", "2002-12-31");
        // when
        ResponseEntity<PersonDTO> response = createPerson(personToCreate);
        // then

    }

    @Test
    void shouldUpdatePerson() {
        //given
        // when
        // then
    }

    @Test
    void shouldDeletePerson() {
        //given
        // when
        // then
    }

    private ResponseEntity<PersonDTO> createPerson (PersonDTO personDTO) {
        return restTemplate.postForEntity(getPeopleApiUrl(), personDTO, PersonDTO.class);
    }

    private String getPeopleApiUrl() {
        return getServerUrl("/api/people/");
    }

    private String getServerUrl(String endpoint) {
        return String.format("http://localhost:%d%s" , localPort, endpoint);
    }


    private ResponseEntity<List<PersonDTO>> getAllPeople() {
        return restTemplate.exchange(
                getPeopleApiUrl(),
                HttpMethod.GET,
                new HttpEntity<>(Collections.emptyMap()),
                new ParameterizedTypeReference<>() {
                }
        );
    }
    private void assertPersonCreated(ResponseEntity<PersonDTO> response, PersonDTO personToCreate) {
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

    }

}
