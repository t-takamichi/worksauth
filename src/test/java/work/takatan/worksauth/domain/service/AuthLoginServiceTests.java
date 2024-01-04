package work.takatan.worksauth.domain.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import work.takatan.worksauth.domain.exception.WorksAuthException;
import work.takatan.worksauth.domain.exception.WorksAuthLoginException;
import work.takatan.worksauth.infrastructure.collection.SnsUser;
import work.takatan.worksauth.infrastructure.repository.SnsUserAuthorizationMongoRepository;
import work.takatan.worksauth.infrastructure.repository.SnsUserMongoRepository;
import work.takatan.worksauth.util.Md5Util;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthLoginServiceTests {

    @Autowired
    private AuthLoginService authLoginService;
    @Autowired
    private SnsUserMongoRepository snsUserMongoRepository;

    @Autowired
    private SnsUserAuthorizationMongoRepository snsUserAuthorizationMongoRepository;

    private String token;

    @BeforeEach
    public void setUp() throws Exception{
        String email = "sample01@email.com";
        String password = Md5Util.md5("hello");
        SnsUser snsUserObject = SnsUser.builder()
                .email(email)
                .password(password)
                .build();
        Optional<SnsUser> snsUser = snsUserMongoRepository.findByEmailAndPassword(email,password);
        if (snsUser.isPresent()) {
            return;
        }
        snsUserMongoRepository.save(snsUserObject);
    }

    @Test
    public void testAuthLoin() {
        String email = "sample01@email.com";
        String password = "hello";
        try {
            var response = authLoginService.handle(email, password);
            Assertions.assertNotNull(response.getAuthID());
            Assertions.assertEquals(response.getEmail(), email);
        } catch (WorksAuthLoginException e) {
            throw new RuntimeException(e.getMessage( ));
        } catch (WorksAuthException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void testFailInAuthLoinEmailEmpty() {
        String email = "";
        String password = "hello";
        Assertions.assertThrows(WorksAuthLoginException.class, () -> authLoginService.handle(email, password));
    }

    @Test
    public void testFailInAuthLoinPasswordEmpty() {
        String email = "sample01@email.com";
        String password = "";
        Assertions.assertThrows(WorksAuthLoginException.class, () -> authLoginService.handle(email, password));
    }


    @Test
    public void testFailInAuthLoginWorksAuthLoginExceptionThrown() {
        String email = "fail@email.com";
        String password = "********";
        Assertions.assertThrows(WorksAuthLoginException.class, () -> authLoginService.handle(email, password));
    }

}
