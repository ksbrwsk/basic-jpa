package de.ksbrwsk.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByOrderByLastNameDesc();

    List<Person> findByLastName(String lastName);


}
