package work.takatan.worksauth.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.takatan.worksauth.domain.exception.UserLoginException;
import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.domain.exception.WorksAuthLoginException;
import work.takatan.worksauth.domain.object.SnsUserObject;
import work.takatan.worksauth.domain.object.auth.Auth;
import work.takatan.worksauth.domain.object.event.SnsUserLoginEvent;
import work.takatan.worksauth.domain.publisher.SnsUserLoinPublisher;
import work.takatan.worksauth.domain.repository.SnsUserRepository;
import work.takatan.worksauth.util.TokenUseParseUtil;
import work.takatan.worksauth.util.Md5Util;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthLoginService {

    private final SnsUserRepository snsUserRepository;

    private final SnsUserLoinPublisher snsUserLoinPublisher;

    public Auth handle(String email, String password) throws WorksAuthException {
        SnsUserObject snsUserObject;
        try {
            var hexedPassword = Md5Util.md5(password);
            snsUserObject = snsUserRepository.findByEmailAndPassword(email, hexedPassword);
        } catch (UserLoginException e) {
            throw new WorksAuthLoginException(e.getMessage());
        } catch (WorksAuthException e) {
            throw new WorksAuthException(e.getMessage());
        }

        var auth = Auth.builder()
                .authID(UUID.randomUUID().toString())
                .token(TokenUseParseUtil.createToken())
                .email(snsUserObject.getEmail()).build();

        SnsUserLoginEvent snsUserLoginEvent = new SnsUserLoginEvent(this, auth, snsUserObject.getId());

        snsUserLoinPublisher.loginPublishEvent(snsUserLoginEvent);

        return auth;
    }
}
