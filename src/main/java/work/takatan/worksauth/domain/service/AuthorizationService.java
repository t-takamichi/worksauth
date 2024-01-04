package work.takatan.worksauth.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.takatan.worksauth.application.request.AuthorizationRequest;
import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.domain.object.auth.Authorization;
import work.takatan.worksauth.domain.repository.SnsUserAuthorizationRepository;
import work.takatan.worksauth.util.TokenUseParseUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final SnsUserAuthorizationRepository snsUserAuthorizationRepository;

    public Authorization handle(AuthorizationRequest authorizationRequest){
        var result = TokenUseParseUtil.validateToken(authorizationRequest.getToken());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date loginDate = Date.from(localDateTime.toInstant(
                ZoneId.systemDefault().getRules().getOffset(localDateTime))
        );

        try {
            var snsUserAuthorization = snsUserAuthorizationRepository.findByToken(authorizationRequest.getToken());
            snsUserAuthorization.setLoginDate(loginDate);
            snsUserAuthorizationRepository.save(snsUserAuthorization);

        } catch (WorksAuthException e) {
            throw new RuntimeException(e);
        }
        return Authorization.builder().build();
    }
}
