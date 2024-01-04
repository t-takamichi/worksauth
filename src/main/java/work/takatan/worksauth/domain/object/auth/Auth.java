package work.takatan.worksauth.domain.object.auth;

import lombok.Builder;
import lombok.Data;

/**
 * 認証のレスポンス
 * @author t.takamichi
 */
@Data
@Builder
public class Auth {
    private String authID;
    private String token;
    private String email;
}
