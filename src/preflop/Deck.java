package preflop;

import java.util.Collections;

public class Deck {
    private Card[] deck = new Card[52];
    private int deckSize = 52;


    public Deck() throws Exception {
        this.newDeck();
    }


    public int newDeck() throws Exception {
        int count = 0;
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 13; y++){
                deck[count] = new Card(x, y);
                count++;
            }
        }
        deckSize = 52;
        return count;

    }

    public Card[] getDeck() {
        return deck;
    }

    public Card getCard() {
        deckSize--;
        return deck[deckSize];
    }

    private Card getCardAtIndex(int index) {
        deckSize--;
        Card temp = deck[index];
        deck[index] = deck[deckSize];
        return temp;

    }


    public int getSize() {
        return deckSize;
    }
    public void shuffle() {
        Card[] tempDeck = new Card[52];
        for(int x = 0; x < 52; x++){
            int index = (int) (Math.random() * deckSize);
            tempDeck[x] = getCardAtIndex(index);
        }
        deck = tempDeck;
        deckSize = 52;


    }
    
}
