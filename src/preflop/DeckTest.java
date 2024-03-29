package preflop;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class DeckTest {

    @Test
    public void newDeck() throws Exception {
        Deck deck = new Deck();

        assertEquals(deck.newDeck(), 52);
        Card[] cards = deck.getDeck();

        for (int x = 0; x < deck.getDeck().length; x++) {
            System.out.print(cards[x].toString() + " ");
        }

        assertEquals(deck.getSize(), 52);
    }

    @Test
    public void testShuffle() throws Exception {
        Deck deck = new Deck();
        deck.shuffle();
        int count = 0;
        while (deck.getSize() > 0) {
            count++;
            Card card = deck.getCard();
            System.out.print(card.toString() + " ");
        }
        System.out.println();
        System.out.println(count);

    }

}