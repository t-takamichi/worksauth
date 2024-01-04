package work.takatan.worksauth.domain.repository;

import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.infrastructure.collection.SnsUserAuthorization;

public interface SnsUserAuthorizationRepository {
    SnsUserAuthorization save(SnsUserAuthorization snsUserAuthorization);
    SnsUserAuthorization findBySnsUserId(String snsUserId) throws WorksAuthException;
    SnsUserAuthorization findByToken(String token) throws WorksAuthException;
}
