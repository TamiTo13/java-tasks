package bg.sofia.uni.fmi.mjt.server.users.session;

import java.util.Random;

public class UniqueIdGenerator {
    private static final int RANGE = 1000;
    public static String generateUniqueId(String username) {
        int hashCode = Math.abs(username.hashCode());

        Random random = new Random();
        int randomNumber = random.nextInt(RANGE);

        String uniqueId = String.format("%d%d", hashCode, randomNumber);

        return uniqueId;
    }
}

