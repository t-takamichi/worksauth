package work.takatan.worksauth.domain.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.domain.object.event.SnsUserLoginEvent;
import work.takatan.worksauth.domain.repository.SnsUserAuthorizationRepository;
import work.takatan.worksauth.infrastructure.collection.SnsUserAuthorization;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@AllArgsConstructor
public class ApplicationListener {
    private final int LIMIT_AUTH_HOUR = 5;

    private final SnsUserAuthorizationRepository snsUserAuthorizationRepository;

    @EventListener
    @Transactional
    public void onHandle(SnsUserLoginEvent snsUserLoginEvent) {

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(LIMIT_AUTH_HOUR);

        Date expireDate = Date.from(localDateTime.toInstant(
                ZoneId.systemDefault().getRules().getOffset(localDateTime))
        );

        SnsUserAuthorization snsUserAuthorization;
        try {
            snsUserAuthorization= snsUserAuthorizationRepository
                    .findBySnsUserId(snsUserLoginEvent.getSnsUserId());

            snsUserAuthorization.setToken(snsUserLoginEvent.getAuth().getToken());
            snsUserAuthorization.setExpireDate(expireDate);
        } catch (WorksAuthException e) {
            snsUserAuthorization = SnsUserAuthorization
                    .builder()
                    .snsUserId(snsUserLoginEvent.getSnsUserId())
                    .token(snsUserLoginEvent.getAuth().getToken())
                    .expireDate(expireDate)
                    .build();
        }
        snsUserAuthorizationRepository.save(snsUserAuthorization);
    }
}
