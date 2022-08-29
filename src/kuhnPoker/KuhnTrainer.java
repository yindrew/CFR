package kuhnPoker;
import java.util.Random;
import java.util.TreeMap;

public class KuhnTrainer {
    public static final int PASS = 0, BET = 1, NUM_ACTIONS = 2;
    public static final Random random = new Random();
    public TreeMap<String, KuhnNode> nodeMap = new TreeMap<String, KuhnNode>();

    public void train(int iterations) {
        int[] cards = { 1, 2, 3 };
        double util = 0;
        for (int i = 0; i < iterations; i++) {
            shuffle(cards);
            util += cfr(cards, "", 1, 1);

        }
        System.out.println("Average game value: " + util / iterations);
        for (KuhnNode n : nodeMap.values())
            System.out.println(n);
    }

    public void shuffle(int[] cards) {
        for (int c1 = cards.length - 1; c1 > 0; c1--) {
            int c2 = random.nextInt(c1 + 1);
            int tmp = cards[c1];
            cards[c1] = cards[c2];
            cards[c2] = tmp;
        }
    }

    private double cfr(int[] cards, String history, double p0, double p1) {
        // sets up which player is going at the moment: player0 or player1
        int plays = history.length();
        int player = plays % 2;
        int opp = 1 - player;

        // returns payoff for terminal states
        if (plays > 1) {
            boolean terminalPass = history.charAt(plays - 1) == 'p';
            boolean doubleBet = history.substring(plays - 2, plays).equals("bb");
            boolean isPlayerCardHigher = cards[player] > cards[opp];
            if (terminalPass)
                if (history.equals("pp"))
                    return isPlayerCardHigher ? 1 : -1;
                else
                    return 1;
            else if (doubleBet)
                return isPlayerCardHigher ? 2 : -2;
        }

        String infoSet = cards[player] + history;

        // get information set node or create if nonexistant
        KuhnNode node = nodeMap.get(infoSet);
        if (node == null) {
            node = new KuhnNode();
            node.infoSet = infoSet;
            nodeMap.put(infoSet, node);
        }
        // for each action, recursively call cfr with additional history and probability
        double[] strategy = node.getStrategy(player == 0 ? p0 : p1);
        double[] util = new double[NUM_ACTIONS];
        double nodeUtil = 0;
        for (int a = 0; a < NUM_ACTIONS; a++) {
            String nextHistory = history + (a == 0 ? "p" : "b");
            util[a] = player == 0 ? -cfr(cards, nextHistory, p0 * strategy[a], p1) : -cfr(cards, nextHistory, p0, p1 * strategy[a]);
            nodeUtil += strategy[a] * util[a];
        }

        // for each action, compute and accumulate counterfactual regret
        for (int a = 0; a < NUM_ACTIONS; a++) {
            double regret = util[a] - nodeUtil;
            node.regretSum[a] += (player == 0 ? p1 : p0) * regret;
        }

        return nodeUtil;

    }

    public static void main(String[] args) {
        int iterations = 10000000;
        new KuhnTrainer().train(iterations);
    }
}


// b

