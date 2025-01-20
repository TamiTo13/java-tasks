package bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand;

import bg.sofia.uni.fmi.mjt.server.command.ALVisible;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseWriter;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.NonExistentAccountException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddAdminUserCommand extends AdminCommand implements ALVisible {

    public static final String COMMAND_NAME = "add-admin-user";

    private static final String EXECUTION_MESSAGE = " has been made to an admin.";

    private static final String PATTERN =
            "add-admin-user --session-id \\S+ --username \\S+";

    public AddAdminUserCommand(String id, String username) {
        super(id, username);
    }

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN);
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
            if (reader1.findUsernameLine(username) == DataBaseReader.NON_EXISTENT_USERNAME) {
                throw new NonExistentAccountException("There isn't acc with this username.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Trouble with opening file: " + e.getMessage());
        }

        DataBaseWriter.changeAdminStatus(username, true);
        ActiveSessions.sessionMap
                .get(ActiveSessions.getUserId(username))
                .getUser().makeAdmin();

        return username + EXECUTION_MESSAGE;
    }

    @Override
    public String getData() {
        return new String("Attempt to add " + username + " as admin");
    }
}
