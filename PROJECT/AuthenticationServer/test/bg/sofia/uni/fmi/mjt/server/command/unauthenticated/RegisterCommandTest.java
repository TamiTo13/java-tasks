package bg.sofia.uni.fmi.mjt.server.command.unauthenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.TakenUsernameException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterCommandTest {


    @Test
    public void testExecuteTakenUsername() throws IOException {
        String username = "testUser";
        String password = "password";
        String firstName = "Tami";
        String lastName = "Rufatov";
        String email = "tami@example.com";
        RegisterCommand registerCommand = new RegisterCommand(username, password, firstName, lastName, email);

        assertThrows(TakenUsernameException.class, () -> registerCommand.execute());
    }
}

