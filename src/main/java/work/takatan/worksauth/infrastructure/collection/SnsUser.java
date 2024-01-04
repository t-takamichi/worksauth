package work.takatan.worksauth.infrastructure.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import work.takatan.worksauth.domain.object.SnsUserObject;

import java.util.Date;

@Data
@Builder
@Document(collection = "SnsUser")
public class SnsUser {
    @Id
    private String id;

    private String password;

    @Indexed(unique = true)
    private String email;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    public SnsUserObject toDomainObject() {
        return SnsUserObject.builder()
                .id(this.id)
                .email(this.email)
                .created(this.created)
                .updated(this.updated)
                .build();
    }
}
