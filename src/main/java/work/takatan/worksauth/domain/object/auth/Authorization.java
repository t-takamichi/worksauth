package work.takatan.worksauth.domain.object.auth;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authorization {

    private AuthorizationStatus authorizationStatus;
}
