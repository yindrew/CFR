package preflop;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void testing() {
        Player player = new Player(100);
        Card[] cards = new Card[2];
        cards[0] = new Card("5s");
        cards[1] = new Card("7s");
        player.setHand(cards);
        System.out.println(player.getHand().handType());
    }

    @Test
    public void testingRange() {
        Player player = new Player(100);
        Card[] cards = new Card[2];
        cards[0] = new Card("Kc");
        cards[1] = new Card("8c");
        player.setHand(cards);

        GameLog gameLog = new GameLog();
        Log log = new Log(5, "r", 2.5);
        Log log1 = new Log(3, "r", 7.5);
        Log log2 = new Log(4, "r", 22);
        gameLog.add(log);
        gameLog.add(log1);
        gameLog.add(log2);
        System.out.println(player.getAction(gameLog, 3));
    }
}
