package bg.sofia.uni.fmi.mjt.server.descriptors;

import bg.sofia.uni.fmi.mjt.server.ServerClass;
import bg.sofia.uni.fmi.mjt.server.descriptors.operations.BufferOperationHandler;
import bg.sofia.uni.fmi.mjt.server.users.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public abstract class DataBaseWriter {
    public static final String DATABASE_DIRECTION = ServerClass.ACCOUNTS;

    public static void register(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_DIRECTION, true))) {
            BufferOperationHandler.register(writer, user);
        } catch (IOException e) {
            System.err.println("Error with registration: " + e.getMessage());
        }
    }

    public static void updateUser(int line, User user) {
        File inputFile = new File(DATABASE_DIRECTION);

        List<String> buffer = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            buffer = BufferOperationHandler.getUpdatedList(reader, line, user);
        } catch (IOException e) {
            System.err.println("Error updating user: " + user.getUsername());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            BufferOperationHandler.writeList(writer, buffer);
        } catch (IOException e) {
            System.err.println("Error updating user: " + user.getUsername());
        }
    }

    public static void deleteLineFromFile(String username) {
        File inputFile = new File(DATABASE_DIRECTION);

        List<String> buffer = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            buffer = BufferOperationHandler.getUpdatedList(reader, username);
        } catch (IOException e) {
            System.err.println("Error deleting user: " + username);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            BufferOperationHandler.writeList(writer, buffer);
        } catch (IOException e) {
            System.err.println("Error deleting user: " + username);
        }
    }

    public static void changeAdminStatus(String username, boolean status) {
        File inputFile = new File(DATABASE_DIRECTION);

        List<String> buffer = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            buffer = BufferOperationHandler.getUpdatedList(reader, username, status);
        } catch (IOException e) {
            System.err.println("Error changing admin status: " + username);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, false))) {
            BufferOperationHandler.writeList(writer, buffer);
        } catch (IOException e) {
            System.err.println("Error changing admin status: " + username);
        }
    }

}
