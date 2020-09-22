package de.ksbrwsk.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Log4j2
@RequiredArgsConstructor
public class PersonWriter implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        // delete all instances
        log.info("deleting all instances");
        this.personRepository.deleteAll();

        // create and save new instances
        log.info("create new instances");
        Stream.of("Meiser,Hans;Beckenbauer,Franz;Doe,John;".split(";"))
                .map(peopleTxt -> peopleTxt.split(","))
                .forEach(tupel -> this.personRepository.save(new Person(null, tupel[0], tupel[1])));

        // select and log all instances
        log.info("find all instances");
        this.personRepository.findAll()
                .stream()
                .forEach(person -> log.info(person.toString()));

        // find all instances, order by last name desc
        log.info("find all instances");
        this.personRepository.findByOrderByLastNameDesc()
                .forEach(person -> log.info(person.toString()));// find all instances, order by last name desc

        log.info("find all instances by last name");
        this.personRepository.findByLastName("Beckenbauer")
                .forEach(person -> log.info(person.toString()));
    }
}
