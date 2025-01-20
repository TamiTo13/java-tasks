package bg.sofia.uni.fmi.mjt.server.executor;

import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.AuthenticatedLoginCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.LogoutCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.ResetPasswordCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.UpdateUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.AddAdminUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.DeleteUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.RemoveAdminCommand;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.LoginCommand;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.RegisterCommand;
import bg.sofia.uni.fmi.mjt.server.executor.parser.CommandParser;
import bg.sofia.uni.fmi.mjt.server.exceptions.WrongSyntaxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandParserTest {

    @Test
    public void testValidCommandUnAuthenticated() throws WrongSyntaxException {
        String validLine = "register --username testUser --password password123 --first-name Tami --last-name ssss --email nz@example.com";
        Command command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof RegisterCommand);

        validLine = "login --username testUser --password password123";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof LoginCommand);
    }

    @Test
    public void testValidCommandAuthenticated() throws WrongSyntaxException {
        String validLine = "reset-password --session-id 1234 --username test --old-password old --new-password new";
        Command command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof ResetPasswordCommand);

        validLine = "login --session-id 1234";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof AuthenticatedLoginCommand);

        validLine = "logout --session-id 1234";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof LogoutCommand);

        validLine = "update-user --session-id 1234 --new-username new --new-email newEmail";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof UpdateUserCommand);
    }

    @Test
    public void testValidCommandAdmin() throws WrongSyntaxException {
        String validLine = "add-admin-user --session-id 1234 --username test";
        Command command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof AddAdminUserCommand);

        validLine = "delete-user --session-id 1234 --username test";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof DeleteUserCommand);

        validLine = "remove-admin-user --session-id 1234 --username test";
        command = CommandParser.parseCommand(validLine);
        assertNotNull(command);
        assertTrue(command instanceof RemoveAdminCommand);
    }

    @Test
    public void testParseCommandWithInvalidCommand() {
        String invalidLine = "login --password 123 --username test";
        assertThrows(WrongSyntaxException.class, () -> {
            CommandParser.parseCommand(invalidLine);
        });
    }

    @Test
    public void testParseCommandWithUnknownCommand() {
        String unknownLine = "something random";
        assertThrows(WrongSyntaxException.class, () -> {
            CommandParser.parseCommand(unknownLine);
        });
    }
}


