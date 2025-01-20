package bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand;

import bg.sofia.uni.fmi.mjt.server.command.authenticated.AuthenticatedCommand;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.UnauthorizedCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

public abstract class AdminCommand extends AuthenticatedCommand {

    protected String username;
    protected AdminCommand(String id, String username) {
        super(id);
        this.username = username;
    }

    public static void authorizeCommand(String sessionId) throws IncorrectCommandException {
        checkId(sessionId);
        if (!ActiveSessions.getSession(sessionId).getUser().isAdmin()) {
            throw new UnauthorizedCommandException("Not admin status");
        }
    }
}
