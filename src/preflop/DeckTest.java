package preflop;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class DeckTest {
    Deck deck = new Deck();

    @Test
    public void newDeck() throws Exception {
        deck.newDeck();
        for(int x = 0; x < deck.getDeck().length; x++){
            System.out.println(deck.getDeck()[x].getCard());
        }

    }

}