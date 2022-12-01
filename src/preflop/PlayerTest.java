package preflop;

import java.util.ArrayList;

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
    public void testingRFI() {
        Player player = new Player(100);
        HandStrength hs = new HandStrength();
        hs.getRange(player.RFI(2));
    }


    @Test
    public void testing3Bet() {
        HandStrength hs = new HandStrength();
        Player player = new Player(100);

        GameLog gameLog = new GameLog();
        Log log = new Log(2, "f", 0);
        Log log1 = new Log(2, "f", 0);
        Log log2 = new Log(2, "r", 2);

        gameLog.add(log);
        gameLog.add(log1);
        gameLog.add(log2);

        int temp = player.getRange(gameLog);
        hs.getRange(temp);
    }
    


    @Test
    public void testingcold4bet() {
        HandStrength hs = new HandStrength();
        Player player = new Player(100);

        GameLog gameLog = new GameLog();
        Log log = new Log(5, "r", 2.5);
        Log log1 = new Log(3, "r", 7.5);
        gameLog.add(log);
        gameLog.add(log1);
        int temp = player.getRange(gameLog);
        hs.getRange(temp);

    }


    @Test
    public void testRaiseFold() {
        HandStrength hs = new HandStrength();

        Player player = new Player(100);
        Card[] cards = new Card[2];
        ArrayList<Integer> positions = new ArrayList<Integer>();
        ArrayList<Double> sizes = new ArrayList<Double>();
        sizes.add(2.5);
        sizes.add(7.5);
        positions.add(0);
        positions.add(1);


        cards[0] = new Card("Ac");
        cards[1] = new Card("Kc");
        player.setHand(cards);
        GameLog gameLog = new GameLog();
        Log log = new Log(5, "r", 2.5);
         gameLog.add(log);

         int x = (player.raiseFold(positions.size(), positions, sizes));
         for (int y = 0; y < x; y++) {
            System.out.println(hs.hands[y]);
         }


         System.out.println(x);







    }
}
