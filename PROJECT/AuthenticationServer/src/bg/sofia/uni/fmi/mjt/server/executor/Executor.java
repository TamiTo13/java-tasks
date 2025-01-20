package bg.sofia.uni.fmi.mjt.server.executor;

import bg.sofia.uni.fmi.mjt.server.command.ALVisible;
import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.descriptors.AuditLogWriter;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.users.ActiveSessions;

import java.net.SocketAddress;

public abstract class Executor {

    private static void refresh() {
        ActiveSessions.removeExpired();
    }

    public static String executeCommand(Command command, SocketAddress clientAddress) throws IncorrectCommandException {
        refresh();

        if (command instanceof ALVisible) {
            AuditLogWriter.writeToAL(command.getData() + " with IP:" + clientAddress);
            String outcome = command.execute();
            AuditLogWriter.writeToAL( outcome + " The user's IP is: " + clientAddress);
            return outcome;
        }
        return command.execute();
    }
}
