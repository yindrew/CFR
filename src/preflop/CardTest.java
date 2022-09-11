package preflop;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CardTest {

    @Test
    public void testConstructor() throws Exception {
        Card card1 = new Card(0, 0);
        Card card2 = new Card(3, 12);

        try {
            Card card3 = new Card(3, 13);
            Card card4 = new Card(4, 12);
            Card card5 = new Card(-1, 5);
            Card card6 = new Card(3, -1);

        } catch (Exception Exception) {
        }

        assertEquals(card1.toString(), "Ac");
        assertEquals(card2.toString(), "Ks");

    }

}