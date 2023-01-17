package preflop;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {


    @Test
    public void testingRFI() {
        Player player = new Player();
        player.hs.getAllHands(40);

        
    }


    @Test
    public void testingBB() {
        Player player = new Player();


        GameLog gameLog = new GameLog();
        Log log = new Log(0, "f", 0);
        Log log1 = new Log(1, "f", 0);
        Log log2 = new Log(2, "f", 0);
        Log log3 = new Log(3, "r", 2);
        Log log4 = new Log(4, "f", 0);

        gameLog.add(log);
        gameLog.add(log1);
        gameLog.add(log2);
        gameLog.add(log3);
        gameLog.add(log4);

        Card[] hand = new Card[2];
        hand[0] = new Card("As");
        hand[1] = new Card("5s");
        player.setHand(hand);

        System.out.println(player.getAction(gameLog, 5).actionTaken);




    }


    @Test
    public void testing3Bet() {
        Player player = new Player();

        GameLog gameLog = new GameLog();
        Log log = new Log(2, "f", 0);
        Log log1 = new Log(2, "f", 0);
        Log log2 = new Log(2, "r", 2);

        gameLog.add(log);
        gameLog.add(log1);
        gameLog.add(log2);

        int temp = player.getRaiseRange(gameLog);
        player.hs.getAllHands(temp);
    }
    


    @Test
    public void testingcold4bet() {
        Player player = new Player();

        GameLog gameLog = new GameLog();
        Log log = new Log(5, "r", 2.5);
        Log log1 = new Log(3, "r", 7.5);
        gameLog.add(log);
        gameLog.add(log1);
        player.getRaiseRange(gameLog);

    }


    @Test
    public void testRaiseFold() {

        Player player = new Player();
        Card[] cards = new Card[2];
        ArrayList<Integer> positions = new ArrayList<Integer>();
        ArrayList<Double> sizes = new ArrayList<Double>();
        sizes.add(2.5);
        sizes.add(7.5);
        positions.add(0);
        positions.add(1);


        cards[0] = new Card("Ac");
        cards[1] = new Card("Qc");
        player.setHand(cards);
        GameLog gameLog = new GameLog();
        Log log = new Log(5, "r", 2.5);
        gameLog.add(log);
        gameLog.add(log);


        System.out.println(player.getAction(gameLog, 2).actionTaken);


    }


}
