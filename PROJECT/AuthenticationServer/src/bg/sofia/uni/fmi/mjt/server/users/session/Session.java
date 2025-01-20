package bg.sofia.uni.fmi.mjt.server.users.session;

import bg.sofia.uni.fmi.mjt.server.ServerClass;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.time.LocalDateTime;

public class Session {
    private LocalDateTime expiration;
    private String id;
    private User user;

    public Session(User user) {
        this.user = user;
        expiration = LocalDateTime.now().plusMinutes(ServerClass.SESSION_DURATION_MINUTES);
        id = UniqueIdGenerator.generateUniqueId(user.getUsername());
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public boolean isExpired() {
        return expiration.isBefore(LocalDateTime.now());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void refreshExpiration() {
        expiration = LocalDateTime.now().plusMinutes(ServerClass.SESSION_DURATION_MINUTES);
    }
}
