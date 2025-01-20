package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

public class LogoutCommand extends AuthenticatedCommand {
    public static final String COMMAND_NAME = "logout";
    private static final String EXECUTION_MESSAGE = "You've been logged out";

    private static final String PATTERN = "logout --session-id \\S+";

    public LogoutCommand(String id) {
        super(id);
    }

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getData() {
        return new String(sessionId + " logging out");
    }

    @Override
    public String execute() throws IncorrectCommandException {
        checkId(sessionId);
        ActiveSessions.logout(sessionId);
        return EXECUTION_MESSAGE;
    }
}
