package bg.sofia.uni.fmi.mjt.server.users;

import bg.sofia.uni.fmi.mjt.server.users.session.Session;

import java.util.HashMap;
import java.util.Map;

public abstract class ActiveSessions {
    public static Map<String, Session> sessionMap = new HashMap<>();

    public static Session getSession(String id) {
        return sessionMap.get(id);
    }

    public static void removeExpired() {
        sessionMap.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public static void addSession(Session session) {
        sessionMap.put(session.getId(), session);
    }

    public static String login(User user) {
        for (Session session: sessionMap.values()) {
            if (session.getUser().getUsername().equals(user.getUsername())) {
                sessionMap.remove(session.getId());
            }
        }
        Session session = new Session(user);
        sessionMap.put(session.getId(), session);
        return session.getId();
    }

    public static void logout(String id) {
        sessionMap.remove(id);
    }

    public static void logoutUsername(String username) {
        sessionMap.remove(getUserId(username));
    }

    public static String getUserId(String username) {
        if (sessionMap.isEmpty() || username == null) {
            return null;
        }
        for (Session session: sessionMap.values()) {
            if (session.getUser().getUsername().equals(username)) {
                return session.getId();
            }
        }
        return null;
    }

    public static User getUser(String username) {
        return sessionMap.get(getUserId(username)).getUser();
    }
}
