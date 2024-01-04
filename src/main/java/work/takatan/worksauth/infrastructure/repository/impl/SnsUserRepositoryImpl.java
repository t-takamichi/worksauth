package work.takatan.worksauth.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import work.takatan.worksauth.domain.exception.UserLoginException;
import work.takatan.worksauth.domain.object.SnsUserObject;
import work.takatan.worksauth.domain.repository.SnsUserRepository;
import work.takatan.worksauth.infrastructure.collection.SnsUser;
import work.takatan.worksauth.infrastructure.repository.SnsUserMongoRepository;

@Repository
@RequiredArgsConstructor
public class SnsUserRepositoryImpl implements SnsUserRepository {

    private final SnsUserMongoRepository snsUserMongoRepository;

    @Override
    public SnsUserObject save(SnsUserObject snsUserObject) {
        return snsUserMongoRepository
                .save(
                        SnsUser.builder()
                                .email(snsUserObject.getEmail())
                                .password(snsUserObject.getPassword())
                                .build()
                )
                .toDomainObject();
    }

    @Override
    public SnsUserObject findByEmailAndPassword(String email, String password) throws UserLoginException {
        return snsUserMongoRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserLoginException("Invalid email or password"))
                .toDomainObject();
    }
}
