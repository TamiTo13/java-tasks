package bg.sofia.uni.fmi.mjt.server.command.unauthenticated;

import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.TakenUsernameException;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseWriter;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterCommand extends UnauthenticatedCommand {

    private String firstName;
    private String lastName;
    private String email;

    public static final String COMMAND_NAME = "register";

    private static final String PATTERN =
            "register --username \\S+ --password \\S+ --first-name \\S+ --last-name \\S+ --email \\S+";

    public RegisterCommand(String username, String password, String firstName, String lastName, String email) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static boolean checkFormat(String command) {
        return Pattern.matches(PATTERN, command);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getData() {
        return new String("New registration command with username and password: " + username + " " + password);
    }

    public String execute() throws IncorrectCommandException {

        int l = DataBaseReader.NON_EXISTENT_USERNAME;
        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {
            DataBaseReader reader1 = new DataBaseReader(reader);
            l = reader1.findUsernameLine(username);
        } catch (IOException e) {
            throw new RuntimeException("Trouble with opening file: " + e.getMessage());
        }
        if (l != DataBaseReader.NON_EXISTENT_USERNAME) {
            throw new TakenUsernameException("This username is already taken, try with another.");
        }

        User user = new User(username, password, firstName, lastName, email);
        DataBaseWriter.register(user);
        return ActiveSessions.login(user);
    }
}
