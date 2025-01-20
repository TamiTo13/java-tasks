package bg.sofia.uni.fmi.mjt.server.command.authenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseWriter;
import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResetPasswordCommand extends AuthenticatedCommand {
    public static final String COMMAND_NAME = "reset-password";
    private static final String EXECUTION_MESSAGE = "Password has been changed successfully";
    String username;
    String oldPassword;
    String newPassword;

    public static final String PATTERN =
            "reset-password --session-id \\S+ --username \\S+ --old-password \\S+ --new-password \\S+";

    public ResetPasswordCommand(String id, String username, String oldPassword, String newPassword) {
        super(id);
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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
        return new String("Password change attempt from session " + sessionId);
    }

    @Override
    public String execute() throws IncorrectCommandException {
        checkId(sessionId);

        User oldUser = ActiveSessions.getSession(this.sessionId).getUser();
        if (!oldUser.getUsername().equals(username) || !PasswordManager.verify(oldPassword, oldUser.getPassword())) {
            throw new InvalidInputException("Wrong username or password.");
        }

        User newUser = new User(oldUser.getUsername(), newPassword,
                oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(), oldUser.isAdmin());

        int line = -1;
        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {
            DataBaseReader reader1 = new DataBaseReader(reader);
            line = reader1.findUsernameLine(oldUser.getUsername());
        } catch (IOException e) {
            throw new RuntimeException("Trouble with opening file: " + e.getMessage());
        }

        DataBaseWriter.updateUser(line, newUser);
        return EXECUTION_MESSAGE;
    }
}
