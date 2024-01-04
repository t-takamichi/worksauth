package work.takatan.worksauth.infrastructure.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "SnsUserAuthorization")
public class SnsUserAuthorization {

    @Id
    private String id;

    private String snsUserId;

    private String token;

    private Date expireDate;

    private Date loginDate;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;
}
