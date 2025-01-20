package bg.sofia.uni.fmi.mjt.server.descriptors.operations;

import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class BufferOperationHandler {

    public static void register(BufferedWriter writer, User user) throws IOException {
        writer.write(User.formatUser(user));
        writer.newLine();
    }

    public static List<String> getUpdatedList(BufferedReader reader, int line, User user) throws IOException {
        List<String> buffer = new LinkedList<>();
        String currentLine;
        int counter = 1;
        while ((currentLine = reader.readLine()) != null) {
            if (counter++ != line) {
                buffer.add(currentLine);
            } else {
                buffer.add(User.formatUser(user));
            }
        }
        return buffer;
    }

    public static void writeList(BufferedWriter writer, List<String> list) throws IOException {
        for (String currentLine: list) {
            writer.write(currentLine);
            writer.newLine();
        }
    }

    public static List<String> getUpdatedList(BufferedReader reader, String username, boolean status)
            throws IOException {
        String currentLine;
        List<String> buffer = new LinkedList<>();
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.startsWith(username + ";")) {
                if (status) {
                    buffer.add(currentLine.replace(";false", ";true"));
                } else {
                    buffer.add(currentLine.replace(";true", ";false"));
                }
            } else {
                buffer.add(currentLine);
            }
        }
        return buffer;
    }

    public static List<String> getUpdatedList(BufferedReader reader, String username) throws IOException {
        String currentLine;
        List<String> buffer = new LinkedList<>();
        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.startsWith(username + ";")) {
                buffer.add(currentLine);
            }
        }
        return buffer;
    }
}
