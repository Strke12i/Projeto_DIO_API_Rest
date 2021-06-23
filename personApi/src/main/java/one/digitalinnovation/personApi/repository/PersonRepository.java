package one.digitalinnovation.personApi.repository;

import one.digitalinnovation.personApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {




}
