package bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.NonExistentAccountException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.UnauthorizedCommandException;
import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;
import bg.sofia.uni.fmi.mjt.server.users.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteUserTest {

    @Test
    public void testDeleteUserCommandNonExistentTest() {
        String username = "testUser";
        String password = "Pass123";

        User user = new User(username, PasswordManager.hashPassword(password), "Tami", "Rufatov", "tami@example.com", false);
        Session session = new Session(user);
        ActiveSessions.addSession(session);
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(session.getId(),username);

        assertThrows(UnauthorizedCommandException.class, ()-> deleteUserCommand.execute());

        ActiveSessions.sessionMap.get(session.getId()).
                getUser().makeAdmin();
        assertThrows(UnauthorizedCommandException.class, ()-> deleteUserCommand.execute());

        ActiveSessions.logout(session.getId());
    }
}
