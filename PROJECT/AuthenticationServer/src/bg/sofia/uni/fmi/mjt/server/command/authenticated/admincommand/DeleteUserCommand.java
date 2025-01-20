package bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand;

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

public class DeleteUserCommand extends AdminCommand {

    public static final String COMMAND_NAME = "delete-user";
    private static final String EXECUTION_MESSAGE = " has been removed from server";

    private static final String PATTERN =
            "delete-user --session-id \\S+ --username \\S+";

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN);
    }

    public DeleteUserCommand(String id, String username) {
        super(id, username);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getData() {
        return new String("Attempt to remove user from database from:" + username);
    }

    @Override
    public String execute() throws IncorrectCommandException {
        authorizeCommand(sessionId);

        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {

            DataBaseReader reader1 = new DataBaseReader(reader);
            User user = reader1.getUser(username);
            if (user == null) {
                throw new NonExistentAccountException("Cant delete because there is no such user");
            }
            if (user.isAdmin()) {
                throw new UnauthorizedCommandException("Deleting admin-users is prohibited.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Trouble with opening file: " + e.getMessage());
        }

        ActiveSessions.logoutUsername(this.username);
        DataBaseWriter.deleteLineFromFile(username);
        return username + EXECUTION_MESSAGE;
    }
}
