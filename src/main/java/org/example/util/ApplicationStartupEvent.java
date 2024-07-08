package org.example.util;

import org.example.dto.PersonDTO;
import org.example.repository.PeopleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final PeopleRepository peopleRepository;

    public ApplicationStartupEvent(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<PersonDTO> people = this.peopleRepository.findAll();
        people.forEach(System.out::println);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
