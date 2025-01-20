package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

public class AuthenticatedLoginCommand extends AuthenticatedCommand {

    public static final String COMMAND_NAME = "login";
    private static final String EXECUTION_MESSAGE = "Successful login";

    private static final String PATTERN =
            "login --session-id \\S+";

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN);
    }

    public AuthenticatedLoginCommand(String id) {
        super(id);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getData() {
        return null;
    }

    public String getId() {
        return sessionId;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        checkId(sessionId);
        ActiveSessions.sessionMap.get(sessionId).refreshExpiration();
        return EXECUTION_MESSAGE;
    }
}
