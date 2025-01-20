package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.command.authenticated.AuthenticatedLoginCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticatedLoginCommandTest {

    private AuthenticatedLoginCommand command;

    @Before
    public void setUp() {
        command = new AuthenticatedLoginCommand("abc123");
    }

    @Test
    public void testCheckFormatValidCommand() {
        assertTrue(AuthenticatedLoginCommand.checkFormat("login --session-id abc123"));
    }

    @Test
    public void testCheckFormatInvalidCommand() {
        assertFalse(AuthenticatedLoginCommand.checkFormat("login"));
    }

    @Test
    public void testGetName() {
        assertEquals("login", command.getName());
    }
}

