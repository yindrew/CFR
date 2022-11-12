package preflop;
import org.junit.Test;


public class HandTest {
    @Test
    public void testing() {
        Card card1 = new Card("Ac");
        Card card2 = new Card("5d");
        Card[] hand = new Card[2];
        hand[0] = card1;
        hand[1] = card2;

        Hand newHand = new Hand(hand);
        System.out.println(newHand.handType());
    }
}
