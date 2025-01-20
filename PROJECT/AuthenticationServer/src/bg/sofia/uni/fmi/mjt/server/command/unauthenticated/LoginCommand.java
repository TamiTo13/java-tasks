package bg.sofia.uni.fmi.mjt.server.command.unauthenticated;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.FailedLoginAttemptException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.descriptors.DataBaseReader;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.NonExistentAccountException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static bg.sofia.uni.fmi.mjt.server.users.LoginChecker.addToLoginCommandMap;

public class LoginCommand extends UnauthenticatedCommand {

    public static final String COMMAND_NAME = "login";

    private static final String PATTERN =
            "login --username \\S+ --password \\S+";

    public static Map<String, LoginCommand> loginCommandMap = new HashMap<>();

    public LoginCommand(String username, String password) {
        super(username, password);
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
        return null;
    }

    public String execute() throws IncorrectCommandException {

        try (BufferedReader reader = new BufferedReader(new FileReader(DataBaseReader.DATA_BASE_DIRECTION))) {

            DataBaseReader reader1 = new DataBaseReader(reader);
            User user = reader1.authenticateUser(username, password);
            return ActiveSessions.login(user);

        } catch (InvalidInputException e) {

            throw new NonExistentAccountException("There is not account with username " + e.getMessage());

        } catch (FailedLoginAttemptException e) {

            addToLoginCommandMap(username);
            throw new FailedLoginAttemptException("Wrong password.");

        } catch (IOException e) {
            throw new RuntimeException("Trouble with file" + e.getMessage());
        }
    }
}
