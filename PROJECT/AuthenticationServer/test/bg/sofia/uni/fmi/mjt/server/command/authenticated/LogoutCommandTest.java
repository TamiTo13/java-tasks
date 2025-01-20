package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogoutCommandTest {

    @Test
    public void testCheckFormatValidCommand() {
        assertTrue(LogoutCommand.checkFormat("logout --session-id abc123"));
    }

    @Test
    public void testCheckFormatInvalidCommand() {
        // Missing session-id parameter
        assertTrue(!LogoutCommand.checkFormat("logout"));
    }

    @Test
    public void testGetName() {
        LogoutCommand command = new LogoutCommand("abc123");
        assertEquals("logout", command.getName());
    }

    /* @Test
    public void testExecute() {
        LogoutCommand command = new LogoutCommand("abc123");
        String result = command.execute();
        assertFalse(ActiveSessions.sessionMap.containsKey(command.sessionId));
        assertEquals("You've been logged out", result);
    } */
}

