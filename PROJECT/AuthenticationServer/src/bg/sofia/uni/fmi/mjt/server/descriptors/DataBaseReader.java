package bg.sofia.uni.fmi.mjt.server.descriptors;

import bg.sofia.uni.fmi.mjt.server.ServerClass;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.FailedLoginAttemptException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.InvalidInputException;
import bg.sofia.uni.fmi.mjt.server.executor.parser.password.PasswordManager;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {
    public static final String DATA_BASE_DIRECTION = ServerClass.ACCOUNTS;

    private static final int LIMIT = 10000;

    private final BufferedReader reader;

    public static final int NON_EXISTENT_USERNAME = -1;

    public DataBaseReader(BufferedReader reader) {
        this.reader = reader;
    }

    public int findUsernameLine(String username) {
        String line;
        int lineNumber = 0;

        try {
            reader.mark(LIMIT);
            while ((line = reader.readLine()) != null) {
                lineNumber = lineNumber + 1;

                if (line.startsWith(username + ";")) {
                    reader.reset();
                    return lineNumber;
                }
            }

            reader.reset();
        } catch (IOException e) {
            System.err.println("Error reading database: " + e.getMessage());
        }
        return NON_EXISTENT_USERNAME;
    }

    public User getUser(String username) {
        String line;
        int lineNumber = 0;

        try {
            reader.mark(LIMIT);
            while ((line = reader.readLine()) != null) {
                lineNumber = lineNumber + 1;

                if (line.startsWith(username + ";")) {
                    reader.reset();
                    return User.of(line);
                }
            }

            reader.reset();
        } catch (IOException e) {
            System.err.println("Error reading database: " + e.getMessage());
        }
        return null;
    }

    public User authenticateUser(String username, String password) throws IncorrectCommandException {
        String line;
        int lineNumber = 1;

        try {
            reader.mark(LIMIT);
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.startsWith(username + ";")) {
                    User user = User.of(line);

                    if (!PasswordManager.verify(password, user.getPassword())) {
                        reader.reset();
                        throw new FailedLoginAttemptException(username);
                    } else {
                        reader.reset();
                        return user;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading database: " + e.getMessage());
        }
        throw new InvalidInputException(username);
    }

    public List<User> getUsers() {
        List<User> retVal = new ArrayList<>();
        try {
            reader.mark(LIMIT);
            String line;

            while ((line = reader.readLine()) != null) {
                retVal.add(User.of(line));
            }
            reader.reset();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return retVal;
    }

}
