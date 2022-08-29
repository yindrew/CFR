package dudo;
import java.util.Random;
import java.util.TreeMap;

public class DudoTrainer {
    public static final Random random = new Random();
    public TreeMap<String, DudoNode> nodeMap = new TreeMap<String, DudoNode>();

    public void train(int iterations){
        double util = 0;
        int[] rolls = new int[2];
        for(int i = 0; i < iterations; i++) {
            rollDice(rolls);
            util += cfr(rolls, "", 1, 1);

        }
    }

    public void rollDice(int[] rolls) {
        for(int x = 0; x < 2; x++){
            int value = random.nextInt(1, 7);
            rolls[x] = value;
        }


        
    }


    private double cfr(int[] cards, String history, double p0, double p1){
        // sets up which player is going 
        int plays = history.length();
        int player = plays % 2;
        int opp = 1 - player;




        return 0;
    }


    
}
