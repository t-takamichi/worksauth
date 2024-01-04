package work.takatan.worksauth.util;

import dev.paseto.jpaseto.PasetoException;
import dev.paseto.jpaseto.Pasetos;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TokenUseParseUtil {

    public static String createToken() {
        SecretKey key = SecretKeyUtil.getSecretKey("pass","salt");
        Instant now = Instant.now();

        // TODO: 後ほどしっかり設定する
        String token = Pasetos.V1.LOCAL.builder()
                .setSharedSecret(key)
                .setIssuedAt(now)
                .setExpiration(now.plus(5, ChronoUnit.HOURS))
                .setAudience("test-tanaka")
                .claim("1d20", new Random().nextInt(20) + 1)
                .compact();

        return token;
    }

    public static boolean validateToken(String token) {
        SecretKey key = SecretKeyUtil.getSecretKey("pass","salt");
        try {
            Pasetos
                .parserBuilder()
                .setSharedSecret(key)
                .build().parse(token);
            return true;
        } catch (PasetoException ex) {
            return false;
        }
    }

}
