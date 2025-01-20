package bg.sofia.uni.fmi.mjt.server.descriptors;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.FailedLoginAttemptException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;
import bg.sofia.uni.fmi.mjt.server.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseReaderTest {

    private DataBaseReader dataBaseReader;
    private final String testData = "user1;" + PasswordManager.hashPassword("password1") + ";Tami;Rufatov;user1@example.com;true" + System.lineSeparator() +
            "user2;" + PasswordManager.hashPassword("password2") + ";Ivan;Ivanov;user2@example.com;false" + System.lineSeparator() +
            "user3;" + PasswordManager.hashPassword("password3") + ";Georgi;Georgiev;user3@example.com;false" + System.lineSeparator();

    @BeforeEach
    void setUp() {
        BufferedReader reader = new BufferedReader(new StringReader(testData));
        dataBaseReader = new DataBaseReader(reader);
    }

    @Test
    void testFindUsernameLine() {
        assertEquals(1, dataBaseReader.findUsernameLine("user1"));
        assertEquals(2, dataBaseReader.findUsernameLine("user2"));
        assertEquals(3, dataBaseReader.findUsernameLine("user3"));
        assertEquals(DataBaseReader.NON_EXISTENT_USERNAME, dataBaseReader.findUsernameLine("user4"));
    }

    @Test
    void testAuthenticateUser() {
        try {
            User user1 = null;
            user1 = dataBaseReader.authenticateUser("user1", "password1");
            assertNotNull(user1);
            assertEquals("user1", user1.getUsername());
            assertEquals("Tami", user1.getFirstName());
            assertEquals("Rufatov", user1.getLastName());
            assertEquals("user1@example.com", user1.getEmail());

            assertThrows(FailedLoginAttemptException.class,
                    () -> dataBaseReader.authenticateUser("user1", "wrongpassword"));
        } catch (IncorrectCommandException e) {
            fail("Unexpected InvalidInputException");
        }
    }

    @Test
    void testGetUsers() {
        List<User> users = dataBaseReader.getUsers();
        assertEquals(3, users.size());

        User user1 = users.get(0);
        assertEquals("user1", user1.getUsername());
        assertEquals(PasswordManager.hashPassword("password1"), user1.getPassword());
        assertEquals("Tami", user1.getFirstName());
        assertEquals("Rufatov", user1.getLastName());
        assertEquals("user1@example.com", user1.getEmail());
    }

    @Test
    public void testGetUser() {
        String input = "user1;pass1;one;one;one@example.com;false\n" +
                "user2;pass2;two;two;two@example.com;true\n" +
                "user3;pass3;three;three;three@example.com;false\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        DataBaseReader dataBaseReader = new DataBaseReader(reader);

        User user = dataBaseReader.getUser("user2");
        assertNotNull(user);
        assertEquals("user2", user.getUsername());
        assertEquals("two", user.getFirstName());
        assertEquals("two", user.getLastName());
        assertEquals("two@example.com", user.getEmail());
        assertTrue(user.isAdmin());

        assertNull(dataBaseReader.getUser("nonexistent"));
    }
}

