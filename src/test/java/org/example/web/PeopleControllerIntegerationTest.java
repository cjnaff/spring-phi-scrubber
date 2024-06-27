package org.example.web;

import org.example.dto.PersonDTO;
import org.example.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    private static List<PersonDTO> testPeople = new ArrayList<>();
    private static PersonDTO testPerson1 = new PersonDTO("First", "Test", "2020-01-01");
    private static PersonDTO testPerson2 = new PersonDTO("Second", "Test", "2020-02-02");
    private static PersonDTO testPerson3 = new PersonDTO("Third", "Test", "2020-03-03");

    public void insertSomePeople() {
        ResponseEntity<PersonDTO> r1 = createPerson(testPerson1);
        ResponseEntity<PersonDTO> r2 = createPerson(testPerson2);
        ResponseEntity<PersonDTO> r3 = createPerson(testPerson3);
        System.out.println("Inserted test people: " + testPerson1 + ", " + testPerson2 + ", " + testPerson3);
    }

    @Test
    void shouldGetPeople() {
        insertSomePeople();
        // when
        ResponseEntity<List<PersonDTO>> response = getAllPeople();
        // then
        assertMovieList(response);
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
        assertPersonCreated(response, personToCreate);
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

    private  String getServerUrl(String endpoint) {
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
        assertThat(response.getBody())
                .isNotNull();
        assertThat(response.getBody().getId())
                .isNotNull()
                .isNotZero();
        assertThat(response.getBody().getFirstName())
                .isEqualTo(personToCreate.getFirstName());
        assertThat(response.getBody().getLastName())
                .isEqualTo(personToCreate.getLastName());
        assertThat(response.getBody().getDob())
                .isEqualTo(personToCreate.getDob());

    }
    private void assertMovieList(ResponseEntity<List<PersonDTO>> response) {
        assertThat(response.getStatusCode())
                .isEqualTo((HttpStatus.OK));
        assertThat(response.getBody())
                .isNotNull()
                .isNotEmpty()
                .containsAll(testPeople);
    }

}
