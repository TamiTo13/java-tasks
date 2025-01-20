package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;
import bg.sofia.uni.fmi.mjt.server.users.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResetPasswordCommandTest {

    @Test
    public void testResetPasswordCommand_InvalidInput() {
        String username = "user123";
        String oldPassword = "oldPass123";
        String newPassword = "newPass456";

        User user = new User("wrongUsername", PasswordManager.hashPassword(oldPassword), "Tami", "Rufatov", "tami@example.com", false);
        Session session = new Session(user);
        ActiveSessions.addSession(session);
        ResetPasswordCommand resetPasswordCommand =
                new ResetPasswordCommand(session.getId(), username, oldPassword, newPassword);
        assertThrows(InvalidInputException.class, ()-> resetPasswordCommand.execute());

        ActiveSessions.logout(session.getId());
    }
}

