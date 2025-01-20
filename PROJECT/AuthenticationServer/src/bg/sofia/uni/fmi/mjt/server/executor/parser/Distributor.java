package bg.sofia.uni.fmi.mjt.server.executor.parser;

import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.AuthenticatedLoginCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.LogoutCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.ResetPasswordCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.UpdateUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.AddAdminUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.DeleteUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.RemoveAdminCommand;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.RegisterCommand;
import bg.sofia.uni.fmi.mjt.server.users.LoginChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Distributor extends CommandParser {
    public static Command passLine(String line, Integer key) {

        return switch (key) {
            case ADD_ADMIN_COMMAND -> createAddAdminCommand(line);
            case DELETE_USER_COMMAND -> createDeleteUserCommand(line);
            case REMOVE_ADMIN_COMMAND -> createRemoveAdminCommand(line);
            case AUTHENTICATED_LOGIN_COMMAND -> createAuthenticatedLoginCommand(line);
            case LOGOUT_COMMAND -> createLogoutCommand(line);
            case RESET_PASSWORD_COMMAND -> createResetPasswordCommand(line);
            case UPDATE_USER_COMMAND -> createUpdateUserCommand(line);
            case LOGIN_COMMAND -> LoginChecker.createLoginCommand(line);
            case REGISTER_COMMAND -> createRegisterCommand(line);
            default -> null;
        };
    }

    public static String extractParameter(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static Command createRegisterCommand(String line) {
        String username = extractParameter(line, "--username (\\S+)");
        String password = extractParameter(line, "--password (\\S+)");
        String firstName = extractParameter(line, "--first-name (\\S+)");
        String lastName = extractParameter(line, "--last-name (\\S+)");
        String email = extractParameter(line, "--email (\\S+)");

        return new RegisterCommand(username, password, firstName, lastName, email);
    }

    public static Command createUpdateUserCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        String username = extractParameter(line, "--new-username (\\S+)");
        String firstName = extractParameter(line, "--new-first-name (\\S+)");
        String lastName = extractParameter(line, "--new-last-name (\\S+)");
        String email = extractParameter(line, "--new-email (\\S+)");

        return new UpdateUserCommand(sessionId, username, firstName, lastName, email);
    }

    public static Command createResetPasswordCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        String username = extractParameter(line, "--username (\\S+)");
        String oldPassword = extractParameter(line, "--old-password (\\S+)");
        String newPassword = extractParameter(line, "--new-password (\\S+)");

        return new ResetPasswordCommand(sessionId, username, oldPassword, newPassword);
    }

    public static Command createLogoutCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        return new LogoutCommand(sessionId);
    }

    public static Command createAuthenticatedLoginCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        return new AuthenticatedLoginCommand(sessionId);
    }

    public static Command createDeleteUserCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        String username = extractParameter(line, "--username (\\S+)");

        return new DeleteUserCommand(sessionId, username);
    }

    public static Command createRemoveAdminCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        String username = extractParameter(line, "--username (\\S+)");

        return new RemoveAdminCommand(sessionId, username);
    }

    public static Command createAddAdminCommand(String line) {
        String sessionId = extractParameter(line, "--session-id (\\S+)");
        String username = extractParameter(line, "--username (\\S+)");

        return new AddAdminUserCommand(sessionId, username);
    }
}
