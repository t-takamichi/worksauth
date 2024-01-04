package work.takatan.worksauth.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import work.takatan.worksauth.infrastructure.collection.SnsUserAuthorization;

import java.util.Optional;

public interface SnsUserAuthorizationMongoRepository extends MongoRepository<SnsUserAuthorization, String> {
    Optional<SnsUserAuthorization> findBySnsUserId(String SnsUserId);
    Optional<SnsUserAuthorization> findByToken(String token);
}
