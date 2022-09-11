package preflop;

import java.util.Collections;

public class Deck {
    private Card[] deck = new Card[52];
    private int deckSize = 52;

    public void newDeck() throws Exception {
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 13; y++){
                deck[x+y] = new Card(x, y);
            }
        }
        deckSize = 52;
    }

    public Card[] getDeck() {
        return deck;
    }

    public Card getCard() {
        return deck[deckSize--];
    }


    public void shuffle() {
        Card[] tempDeck = new Card[52];


    }
    
}
