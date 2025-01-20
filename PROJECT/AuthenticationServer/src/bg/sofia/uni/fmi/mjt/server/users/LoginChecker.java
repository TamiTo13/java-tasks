package bg.sofia.uni.fmi.mjt.server.users;

import bg.sofia.uni.fmi.mjt.server.ServerClass;
import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.LoginCommand;
import bg.sofia.uni.fmi.mjt.server.descriptors.AuditLogWriter;
import bg.sofia.uni.fmi.mjt.server.exceptions.BannedException;
import bg.sofia.uni.fmi.mjt.server.executor.parser.Distributor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class LoginChecker {
    private static Map<String, List<LocalDateTime>> loginCommandMap = new HashMap<>();
    private static Map<String, LocalDateTime> bannedUsers  = new HashMap<>();
    private static final Integer LOGIN_ATTEMPT_RANGE = 5;

    public static Command createLoginCommand(String line) {
        String username = Distributor.extractParameter(line, "--username (\\S+)");
        String password = Distributor.extractParameter(line, "--password (\\S+)");

        if ( bannedUsers.containsKey(username) && bannedUsers.get(username).isAfter(LocalDateTime.now()) ) {
            throw new BannedException("You cant login now, try again later");
        } else if (bannedUsers.containsKey(username) && bannedUsers.get(username).isBefore(LocalDateTime.now())) {
            bannedUsers.remove(username);
        }

        return new LoginCommand(username, password);
    }

    public static void addToLoginCommandMap(String username) {
        if (loginCommandMap.containsKey(username)) {
            if (loginCommandMap.get(username)
                    .getLast()
                    .plusMinutes(LOGIN_ATTEMPT_RANGE)
                    .isBefore(LocalDateTime.now())) {
                loginCommandMap.get(username).clear();
            }
            loginCommandMap.get(username).add(LocalDateTime.now());

        } else {
            loginCommandMap.put(username, new LinkedList<>());
            loginCommandMap.get(username).add(LocalDateTime.now());
        }

        AuditLogWriter.writeToAL("Failed login by-" + username);

        if (loginCommandMap.get(username).size() >= ServerClass.LOGIN_ATTEMPTS) {
            bannedUsers.put(username, LocalDateTime.now().plusMinutes(ServerClass.BLOCK_TIME));
            loginCommandMap.remove(username);
        }
    }

}
