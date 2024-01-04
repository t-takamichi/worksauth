package work.takatan.worksauth.application.request;

import lombok.Data;

@Data
public class AuthorizationRequest {
    private final String token;
}
