package rps;

import java.util.Arrays;
import java.util.Random;
import java.text.DecimalFormat;


public class RPS {
    DecimalFormat df = new DecimalFormat("#.###");
    public static final int ROCK = 0, PAPER = 1, SCISSORS = 2, NUM_ACTIONS = 3;
    public static final Random random = new Random();
    double[] regretSum = new double[NUM_ACTIONS],
            strategy = new double[NUM_ACTIONS],
            strategySum = new double[NUM_ACTIONS],
            oppRegretSum = new double[NUM_ACTIONS],
            oppStrategy = new double[NUM_ACTIONS],
            oppStrategySum = new double[NUM_ACTIONS],
            badStrat = {.4, .3, .3};

    /**
     * getting the overarching strategy
     * 
     * @return the frequencies at which to choose RPS
     */
    public double[] getStrategy() {
        double normalizingSum = 0;
        // for each action (Rock Paper or Scissors)
        for (int a = 0; a < NUM_ACTIONS; a++) {
            // set the values of strategy to amount of regret or 0 if regret is <= 0.
            strategy[a] = regretSum[a] > 0 ? regretSum[a] : 0;
            normalizingSum += strategy[a];
        }
        // turn the values into frequencies
        for (int a = 0; a < NUM_ACTIONS; a++) {
            if (normalizingSum > 0)
                strategy[a] /= normalizingSum;
            else
                strategy[a] = 1.0 / NUM_ACTIONS;
            strategySum[a] += strategy[a];
        }
        return strategy;
    }

    /**
     * determine a certain action
     * 
     * @param strategy the frequencies at which to choose RPS
     * @return
     */
    public int getAction(double[] strategy) {
        double r = random.nextDouble();
        int a = 0;
        double cumulativeProbability = 0;
        while (a < NUM_ACTIONS - 1) {
            cumulativeProbability += strategy[a];
            if (r < cumulativeProbability)
                break;
            a++;
        }
        return a;
    }

    public double[] getOppStrategy() {
        double normalizingSum = 0;
        for (int a = 0; a < NUM_ACTIONS; a++) {
            oppStrategy[a] = oppRegretSum[a] > 0 ? oppRegretSum[a] : 0;
            normalizingSum += oppStrategy[a];
        }
        for (int a = 0; a < NUM_ACTIONS; a++) {
            if (normalizingSum > 0)
                oppStrategy[a] /= normalizingSum;
            else
                oppStrategy[a] = 1.0 / NUM_ACTIONS;
            oppStrategySum[a] += oppStrategy[a];
        }
        return oppStrategy;
    }

    /**
     * training the bot
     * 
     * @param iterations number of iterations in which to train
     */
    public void train(int iterations) {
        double[] actionUtility = new double[NUM_ACTIONS];
        double[] oppActionUtility = new double[NUM_ACTIONS];
        // loops thru a number of times to get better
        for (int x = 0; x < iterations; x++) {
            double[] strat = getStrategy();
            double[] oppStrategy = getOppStrategy();
            int myAction = getAction(strat);
            int otherAction = getAction(oppStrategy);

            oppActionUtility[myAction] = 0;
            oppActionUtility[(myAction + 1) % NUM_ACTIONS] = 1;
            oppActionUtility[(myAction + 2) % NUM_ACTIONS] = -1;
            // calculates the action utility - how much utility would we have gotte
            // if we had choosen a different action
            actionUtility[otherAction] = 0;
            actionUtility[(otherAction + 1) % NUM_ACTIONS] = 1;
            actionUtility[(otherAction + 2) % NUM_ACTIONS] = -1;

            for (int y = 0; y < NUM_ACTIONS; y++) {
                // calculatates regret by adding the difference between
                // our actual action and the other actions not chosen.
                regretSum[y] += actionUtility[y] - actionUtility[myAction];
                oppRegretSum[y] += oppActionUtility[y] - actionUtility[otherAction];

            }




        
        }
        double[] avgStrat = this.getAverageStrategy(this.strategySum);
        double[] oppAvgStrat = this.getAverageStrategy(this.oppStrategySum);
        
        System.out.println();
        System.out.println("      rock  paper  scissors");
        System.out.println("Bot1: " + df.format(avgStrat[0]) + " " + df.format(avgStrat[1]) + " " + df.format(avgStrat[2]));
        System.out.println("Bot2: " + df.format(oppAvgStrat[0]) + " " + df.format(oppAvgStrat[1]) + " " + df.format(oppAvgStrat[2]));
        System.out.println();

    }
            /**
     * training the bot
     * 
     * @param iterations number of iterations in which to train
     */
    public void trainBad(int iterations) {
        double[] actionUtility = new double[NUM_ACTIONS];
        // loops thru a number of times to get better
        for (int x = 0; x < iterations; x++) {
            double[] strat = getStrategy();
            int myAction = getAction(strat);
            int otherAction = getAction(badStrat);

            actionUtility[otherAction] = 0;
            actionUtility[(otherAction + 1) % NUM_ACTIONS] = 1;
            actionUtility[(otherAction + 2) % NUM_ACTIONS] = -1;

            for (int y = 0; y < NUM_ACTIONS; y++) {
                // calculatates regret by adding the difference between
                // our actual action and the other actions not chosen.
                regretSum[y] += actionUtility[y] - actionUtility[myAction];
            }

        }


        double[] avgStrat = this.getAverageStrategy(this.strategySum);

        System.out.println(); 
        System.out.println("         rock   paper  scissors");
        System.out.println("Player:  " + df.format(badStrat[0]) + "    " + df.format(badStrat[1]) + "    " +df.format(badStrat[2]));
        System.out.println("Bot:     " + df.format(avgStrat[0]) + "      " + df.format(avgStrat[1]) + "       " +df.format(avgStrat[2]));
        System.out.println();

    }

    /**
     * finds the final average strategy after a cetain number of hands
     * 
     * @param strat the strategy used
     * @return the average strategy
     */
    public double[] getAverageStrategy(double[] strat) {
        double[] avgStrat = new double[NUM_ACTIONS];
        double normalizingSum = 0;
        for (int x = 0; x < NUM_ACTIONS; x++) {
            normalizingSum += strat[x];
        }
        for (int x = 0; x < NUM_ACTIONS; x++) {
            if (normalizingSum > 0) {
                avgStrat[x] = strat[x] / normalizingSum;
            } else {
                avgStrat[x] = 1.0 / NUM_ACTIONS;
            }

        }

        return avgStrat;
    }

    public static void main(String[] args) {
        int iterations = 10000000;
        new RPS().train(iterations);
        //new RPS().trainBad(iterations);
    }

}
