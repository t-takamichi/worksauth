package work.takatan.worksauth.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import work.takatan.worksauth.infrastructure.collection.Person;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
