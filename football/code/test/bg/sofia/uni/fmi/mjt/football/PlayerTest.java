package bg.sofia.uni.fmi.mjt.football;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    void testPlayer() {
        Player player = new Player(null, null, null,
                0, 0,0, null, null, 0,
                0, 0, 0, null);
        Player player1 = Player.of("Messi;Lionel Andrés Messi Cuccittini;6/24/1987;31;170.18;72.1;CF,RW,ST;Argentina;94;94;110500000;565000;Left");
        assertThrows(IllegalArgumentException.class, ()->Player.of(null));
    }
}
