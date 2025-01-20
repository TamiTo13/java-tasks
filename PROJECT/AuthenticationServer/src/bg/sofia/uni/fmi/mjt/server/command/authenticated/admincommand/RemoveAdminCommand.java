package bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand;

import bg.sofia.uni.fmi.mjt.server.command.ALVisible;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseWriter;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.NonExistentAccountException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.UnauthorizedCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class RemoveAdminCommand extends AdminCommand implements ALVisible {
    public static final String COMMAND_NAME = "remove-admin-user";
    private static final String EXECUTION_MESSAGE = " has been removed as admin.";

    private static final String PATTERN =
            "remove-admin-user --session-id \\S+ --username \\S+";

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN);
    }

    public RemoveAdminCommand(String id, String username) {
        super(id, username);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        authorizeCommand(sessionId);

        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {

            DataBaseReader reader1 = new DataBaseReader(reader);

            List<User> userList = reader1.getUsers();
            if (userList.stream().filter(User::isAdmin).count() <= 1) {
                throw new UnauthorizedCommandException("There is only one admin in the server");
            }

            if (reader1.findUsernameLine(username) == DataBaseReader.NON_EXISTENT_USERNAME) {
                throw new NonExistentAccountException("There isnt acc with this username.");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        DataBaseWriter.changeAdminStatus(username, false);
        String userID = ActiveSessions.getUserId(username);
        if (userID != null) {
            ActiveSessions.sessionMap.get(userID)
                    .getUser().removeAsAdmin();
        }
        return username + " " + EXECUTION_MESSAGE;
    }

    @Override
    public String getData() {
        return new String("Attempt to remove " + username + " as admin");
    }
}
