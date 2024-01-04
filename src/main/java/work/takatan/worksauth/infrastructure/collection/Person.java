package work.takatan.worksauth.infrastructure.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "person")
public class Person {
    @Id
    private String personId;
    private String firstName;
    private String lastName;
    private Integer age;
}
