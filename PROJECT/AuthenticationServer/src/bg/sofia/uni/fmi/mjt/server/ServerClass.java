package bg.sofia.uni.fmi.mjt.server;

import bg.sofia.uni.fmi.mjt.server.exceptions.BannedException;
import bg.sofia.uni.fmi.mjt.server.exceptions.WrongSyntaxException;
import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;
import bg.sofia.uni.fmi.mjt.server.executor.Executor;
import bg.sofia.uni.fmi.mjt.server.executor.parser.CommandParser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ServerClass {
    public static final int PORT = 8263;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 1024;

    public static final String ACCOUNTS = "accounts.txt";
    public static final int SESSION_DURATION_MINUTES = 20;
    public static final int BLOCK_TIME = 5;

    public static final String AUDIT_LOG = "auditLog.txt";
    public static final int LOGIN_ATTEMPTS = 4;

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(SERVER_HOST, PORT));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        handleReadableKey(key, buffer);
                    } else if (key.isAcceptable()) {
                        handleAcceptableKey(key, selector);
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Issue with the server socket", e);
        }
    }

    private static void handleReadableKey(SelectionKey key, ByteBuffer buffer) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        buffer.clear();
        int r = sc.read(buffer);

        if (r < 0) {
            System.out.println("Client has closed the connection");
            sc.close();
            return;
        }

        buffer.flip();
        byte[] clientInputBytes = new byte[buffer.remaining()];
        buffer.get(clientInputBytes);
        String input = new String(clientInputBytes, StandardCharsets.UTF_8);

        String response;
        try {
            response = Executor.executeCommand(CommandParser.parseCommand(input), sc.getRemoteAddress());
        } catch (WrongSyntaxException | IncorrectCommandException | BannedException e) {
            response = e.getMessage();
        }

        buffer.clear();
        buffer.put(response.getBytes());
        buffer.flip();
        sc.write(buffer);
    }

    private static void handleAcceptableKey(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel sockChannel = (ServerSocketChannel) key.channel();
        SocketChannel accept = sockChannel.accept();
        accept.configureBlocking(false);
        accept.register(selector, SelectionKey.OP_READ);
    }
}

