package bg.sofia.uni.fmi.mjt.server.executor.descriptors;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

public class DataBaseReaderTest {
    @Mock
    private BufferedReader mockBufferedReader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUsernameLineUsernameExists() throws IOException {
    }
}
