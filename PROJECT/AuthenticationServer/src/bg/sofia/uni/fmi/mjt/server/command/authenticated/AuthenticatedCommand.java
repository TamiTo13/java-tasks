package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

public abstract class AuthenticatedCommand implements Command {
    protected String sessionId;

    protected AuthenticatedCommand(String id) {
        sessionId = id;
    }

    public static void checkId(String sessionId) throws InvalidInputException {
        if (!ActiveSessions.sessionMap.containsKey(sessionId)) {
            throw new InvalidInputException("There isn't an active session with this id.");
        }
    }
}