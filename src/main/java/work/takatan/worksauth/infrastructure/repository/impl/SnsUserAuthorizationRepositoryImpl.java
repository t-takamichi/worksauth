package work.takatan.worksauth.infrastructure.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.domain.repository.SnsUserAuthorizationRepository;
import work.takatan.worksauth.infrastructure.collection.SnsUserAuthorization;
import work.takatan.worksauth.infrastructure.repository.SnsUserAuthorizationMongoRepository;

@Repository
@RequiredArgsConstructor
public class SnsUserAuthorizationRepositoryImpl implements SnsUserAuthorizationRepository {
    private final SnsUserAuthorizationMongoRepository snsUserAuthorizationMongoRepository;

    @Override
    public SnsUserAuthorization save(SnsUserAuthorization snsUserAuthorization) {
        return snsUserAuthorizationMongoRepository.save(snsUserAuthorization);
    }

    @Override
    public SnsUserAuthorization findBySnsUserId(String snsUserId) throws WorksAuthException {
        return snsUserAuthorizationMongoRepository.findBySnsUserId(snsUserId)
                .orElseThrow(() -> new WorksAuthException("Not Found"));
    }

    @Override
    public SnsUserAuthorization findByToken(String token) throws WorksAuthException {
        return snsUserAuthorizationMongoRepository.findByToken(token)
                .orElseThrow(() -> new WorksAuthException("Not Found"));
    }
}
