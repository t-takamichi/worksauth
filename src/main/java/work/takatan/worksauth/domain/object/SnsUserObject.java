package work.takatan.worksauth.domain.object;

import lombok.Builder;
import lombok.Data;


import java.util.Date;

@Data
@Builder
public class SnsUserObject {
    private String id;
    private String email;
    private String password;
    private Date created;
    private Date updated;
}