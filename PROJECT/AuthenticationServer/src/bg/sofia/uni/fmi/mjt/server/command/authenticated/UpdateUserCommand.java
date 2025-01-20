package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseWriter;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UpdateUserCommand extends AuthenticatedCommand {

    public static final String COMMAND_NAME = "update-user";
    private static final String EXECUTION_MESSAGE = "User data has been updated successfully";

    String newUsername;
    String newFirstName;
    String newLastName;
    String newEmail;

    public static final String PATTERN =
            "^update-user --session-id \\S+" +
                    "( --new-username \\S+)?( --new-first-name \\S+)?( --new-last-name \\S+)?( --new-email \\S+)?$";

    public static boolean checkFormat(String command) {
        return command.matches(PATTERN); }

    public UpdateUserCommand(String id, String newUsername, String newFirstName,
                                String newLastName, String newEmail) {
        super(id);
        this.newUsername = newUsername;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newEmail = newEmail;
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getData() {
        return new String("Updating user attempt with session id: " + sessionId);
    }

    private User getUser(String newUsername, String newFirstName, String newLastName, String newEmail) {
        User current = ActiveSessions.getSession(sessionId).getUser();
        if (newUsername == null) {
            this.newUsername = current.getUsername();
        }
        if (newFirstName == null) {
            this.newFirstName = current.getFirstName();
        }
        if (newLastName == null) {
            this.newLastName = current.getLastName();
        }
        if (newEmail == null) {
            this.newEmail = current.getEmail();
        }
        return current;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        checkId(this.sessionId);

        User current = getUser(newUsername, newFirstName, newLastName, newEmail);

        User newUser = new User(newUsername, current.getPassword(),
                newFirstName, newLastName, newEmail, current.isAdmin());

        int line = DataBaseReader.NON_EXISTENT_USERNAME;
        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {

            DataBaseReader reader1 = new DataBaseReader(reader);
            User user = ActiveSessions.getSession(this.sessionId).getUser();
            line = reader1.findUsernameLine(user.getUsername());

        } catch (IOException e) {
            throw new RuntimeException("Trouble with opening file: " + e.getMessage());
        }

        DataBaseWriter.updateUser(line, newUser);
        return EXECUTION_MESSAGE;
    }
}
