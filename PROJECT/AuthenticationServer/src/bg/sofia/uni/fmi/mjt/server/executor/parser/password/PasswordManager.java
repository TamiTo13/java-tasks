package bg.sofia.uni.fmi.mjt.server.executor.parser.password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class PasswordManager {
    private static final MessageDigest MESSAGE_DIGEST;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String hashPassword(String password) {
        return Base64.getEncoder()
                .encodeToString(MESSAGE_DIGEST.digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean verify(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }
}
