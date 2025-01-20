package bg.sofia.uni.fmi.mjt.server.command.unauthenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.FailedLoginAttemptException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.NonExistentAccountException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginCommandTest {

    @Test
    public void testExecuteNonExistentAccount() throws IOException {
        String username = "nonExistentUser123";
        String password = "password";

        LoginCommand loginCommand = new LoginCommand(username, password);

        assertThrows(NonExistentAccountException.class, () -> loginCommand.execute());
    }

    @Test
    public void testExecuteIncorrectPassword() throws IOException {
        String username = "testUser";
        String password = "incorrectPassword";
        LoginCommand loginCommand = new LoginCommand(username, password);

        assertThrows(FailedLoginAttemptException.class, () -> loginCommand.execute());
    }
}


