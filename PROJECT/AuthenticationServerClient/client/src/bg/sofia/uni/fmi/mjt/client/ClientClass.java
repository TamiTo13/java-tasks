package bg.sofia.uni.fmi.mjt.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientClass {

    private static final int SERVER_PORT = 8263;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 512;

    private static final String DISCONNECT_MESSAGE = "disconnect";

    private static ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);

    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            connectToServer(socketChannel);

            handleUserInput(scanner, socketChannel);

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }

    private static void connectToServer(SocketChannel socketChannel) throws IOException {
        socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        System.out.println("Connected to the server.");
    }

    private static void handleUserInput(Scanner scanner, SocketChannel socketChannel) throws IOException {
        while (true) {
            System.out.print("Enter command: ");
            String message = scanner.nextLine();

            if (DISCONNECT_MESSAGE.equals(message)) {
                break;
            }

            sendMessageToServer(message, socketChannel);
            receiveReplyFromServer(socketChannel);
        }
    }

    private static void sendMessageToServer(String message, SocketChannel socketChannel) throws IOException {
        System.out.println("Sending message <" + message + "> to the server...");

        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
    }

    private static void receiveReplyFromServer(SocketChannel socketChannel) throws IOException {
        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();

        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        String reply = new String(byteArray, "UTF-8");

        System.out.println("Server replied: " + reply);
    }
}
