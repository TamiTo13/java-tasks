package bg.sofia.uni.fmi.mjt.server.descriptors;

import bg.sofia.uni.fmi.mjt.server.descriptors.operations.BufferOperationHandler;
import bg.sofia.uni.fmi.mjt.server.users.User;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileManagerTest {

    private String input = "user1;pass1;Tami;Rufatov;tami@example.com;false"+ System.lineSeparator() +
            "user2;pass2;Kalata;Cvetkov;kalata@example.com;true" + System.lineSeparator() +
            "user3;pass3;Ivo;Jelezarov;ivo@example.com;false" + System.lineSeparator();
    private BufferedReader reader = new BufferedReader(new StringReader(input));

    @Test
    public void testGetUpdatedListByLine() throws IOException {


        User user = new User("user2", "newpass", "Kalata", "Cvetkov", "kalata@example.com");
        List<String> updatedList = BufferOperationHandler.getUpdatedList(reader, 2, user);

        assertEquals(3, updatedList.size());
        assertEquals("user1;pass1;Tami;Rufatov;tami@example.com;false", updatedList.get(0));
        assertEquals("user3;pass3;Ivo;Jelezarov;ivo@example.com;false", updatedList.get(2));
    }

    @Test
    public void testWriteList() throws IOException {
        // Prepare test data
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        List<String> list = List.of("line1", "line2", "line3");

        // Call the method
        BufferOperationHandler.writeList(writer, list);
        writer.close();

        // Check the output
        String expected = "line1" + System.lineSeparator() +
                "line2" + System.lineSeparator() + "line3" + System.lineSeparator();
        assertEquals(expected, stringWriter.toString());
    }

    @Test
    public void testGetUpdatedList() throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        reader.mark(1000);

        List<String> updatedList = BufferOperationHandler.getUpdatedList(reader, "user3", true);

        assertEquals(3, updatedList.size());
        assertEquals("user3;pass3;Ivo;Jelezarov;ivo@example.com;true", updatedList.get(2));

        reader.reset();
        updatedList = BufferOperationHandler.getUpdatedList(reader, "user3", false);

        assertEquals(3, updatedList.size());
        assertEquals("user3;pass3;Ivo;Jelezarov;ivo@example.com;false", updatedList.get(2));

        reader.reset();
        updatedList = BufferOperationHandler.getUpdatedList(reader, "user3");
        assertEquals(2, updatedList.size());
    }
}



