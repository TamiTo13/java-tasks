package bg.sofia.uni.fmi.mjt.server.users.session;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniqueIdGeneratorTest {

    @Test
    public void testGenerateUniqueIdNotNull() {
        String username = "testUser";
        String uniqueId = UniqueIdGenerator.generateUniqueId(username);
        assertNotNull(uniqueId);
    }

    @Test
    public void testGenerateUniqueIdUnique() {
        String username1 = "user1";
        String username2 = "user2";
        String uniqueId1 = UniqueIdGenerator.generateUniqueId(username1);
        String uniqueId2 = UniqueIdGenerator.generateUniqueId(username2);
        assertNotEquals(uniqueId1, uniqueId2);
    }


    @Test
    public void testGenerateUniqueIdLength() {
        String username = "testUser";
        String uniqueId = UniqueIdGenerator.generateUniqueId(username);
        assertEquals(13, uniqueId.length());
    }
}

