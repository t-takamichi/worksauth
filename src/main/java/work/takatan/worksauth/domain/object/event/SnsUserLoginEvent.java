package work.takatan.worksauth.domain.object.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import work.takatan.worksauth.domain.object.auth.Auth;

public class SnsUserLoginEvent extends ApplicationEvent {

    @Getter
    private Auth auth;

    @Getter
    private String snsUserId;

    public SnsUserLoginEvent(Object source, Auth auth, String snsUserId) {
        super(source);
        this.auth = auth;
        this.snsUserId = snsUserId;
    }
}
