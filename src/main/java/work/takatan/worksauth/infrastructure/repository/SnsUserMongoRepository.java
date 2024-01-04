package work.takatan.worksauth.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import work.takatan.worksauth.infrastructure.collection.SnsUser;

import java.util.Optional;

public interface SnsUserMongoRepository extends MongoRepository<SnsUser, String> {

    SnsUser save(SnsUser snsUser);
    Optional<SnsUser> findByEmailAndPassword(String email, String Password);
}
