package bg.sofia.uni.fmi.mjt.server.users;

import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;
import bg.sofia.uni.fmi.mjt.server.users.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ActiveSessionsTest {

    private Session testSession;
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("testUser", "password123", "Tami", "Rufatov", "mrazqJava@example.com");
        testSession = new Session(testUser);
    }

    @Test
    public void testAddSession() {
        ActiveSessions.addSession(testSession);
        assertEquals(testSession, ActiveSessions.getSession(testSession.getId()));
        ActiveSessions.logout(testSession.getId());
    }

    @Test
    public void testGetSession() {
        ActiveSessions.addSession(testSession);
        assertEquals(testSession, ActiveSessions.getSession(testSession.getId()));
        ActiveSessions.logout(testSession.getId());
    }

    @Test
    public void testLogout() {
        ActiveSessions.addSession(testSession);
        ActiveSessions.logout(testSession.getId());
        assertNull(ActiveSessions.getSession(testSession.getId()));
        ActiveSessions.logout(testSession.getId());
    }

    @Test
    public void testLogoutUsername() {
        ActiveSessions.addSession(testSession);
        ActiveSessions.logoutUsername(testUser.getUsername());
        assertNull(ActiveSessions.getSession(testSession.getId()));
        ActiveSessions.logout(testSession.getId());
    }

    @Test
    public void testGetUserId() {
        ActiveSessions.addSession(testSession);
        assertEquals(testSession.getId(), ActiveSessions.getUserId(testUser.getUsername()));
        ActiveSessions.logout(testSession.getId());
    }

    @Test
    public void testGetUser() {
        ActiveSessions.addSession(testSession);
        assertEquals(testUser, ActiveSessions.getUser(testUser.getUsername()));
        ActiveSessions.logout(testSession.getId());
    }
}

