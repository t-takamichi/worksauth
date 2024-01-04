package work.takatan.worksauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class WorksAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorksAuthApplication.class, args);
	}

}
