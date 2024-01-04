package work.takatan.worksauth.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import work.takatan.worksauth.infrastructure.collection.Person;
import work.takatan.worksauth.infrastructure.repository.PersonRepository;

import java.util.Random;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonTests {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testA() {
//        var aa = personRepository.findAll();
        Random random = new Random();

        Integer randnum = random.nextInt(10);
        var last = randnum.toString();
        var person = Person.builder()
                .age(12)
                .firstName("test")
                .lastName(last)
                .build();

        var response = personRepository.save(person).getPersonId();
        System.out.println(response);

        var sam = personRepository.findByFirstNameAndLastName("test",last);
        sam.get().getPersonId();
        System.out.println(sam);

        personRepository.deleteAll();
    }
}
