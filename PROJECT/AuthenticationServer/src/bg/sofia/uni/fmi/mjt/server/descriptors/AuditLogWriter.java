package bg.sofia.uni.fmi.mjt.server.descriptors;

import bg.sofia.uni.fmi.mjt.server.ServerClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AuditLogWriter {
    private static final String FILE_NAME = ServerClass.AUDIT_LOG;

    public static void writeToAL(String note) {

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(formattedDateTime + " - " + note);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to audit log: " + e.getMessage());
        }
    }
}
