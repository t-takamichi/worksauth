package work.takatan.worksauth.domain.repository;

import work.takatan.worksauth.domain.exception.UserLoginException;
import work.takatan.worksauth.domain.object.SnsUserObject;

public interface SnsUserRepository {

    SnsUserObject save(SnsUserObject snsUserObject);

    SnsUserObject findByEmailAndPassword(String email, String password) throws UserLoginException;
}
