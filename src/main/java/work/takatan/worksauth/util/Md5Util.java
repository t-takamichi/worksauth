package work.takatan.worksauth.util;

import work.takatan.worksauth.domain.exception.WorksAuthException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String md5(String text) throws WorksAuthException{
        try {
            var md5 = MessageDigest.getInstance("MD5");
            var digest = md5.digest(text.getBytes());
            return DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new WorksAuthException(e.getMessage());
        }
    }
}
