package preflop;

import org.junit.Test;

public class DealHandTest {
    
    @Test
    public void testing() throws Exception {
        Deck newDeck = new Deck();
        Player player = new Player();
        player.setHand(newDeck.dealHand());

        System.out.println(player.handToString());


    }

    
}
