package work.takatan.worksauth.domain.publisher;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import work.takatan.worksauth.domain.object.event.SnsUserLoginEvent;

@Component
@AllArgsConstructor
@Async
public class SnsUserLoinPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void loginPublishEvent(SnsUserLoginEvent snsUserLoginEvent) {
        applicationEventPublisher.publishEvent(snsUserLoginEvent);
    }
}
