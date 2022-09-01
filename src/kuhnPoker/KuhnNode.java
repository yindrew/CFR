package kuhnPoker;
import java.text.DecimalFormat;
import java.util.Arrays;

public class KuhnNode {
    DecimalFormat df = new DecimalFormat("#.###");
    String infoSet;
    int NUM_ACTIONS = 2;
    double[] regretSum = new double[NUM_ACTIONS],
            strategy = new double[NUM_ACTIONS],
            strategySum = new double[NUM_ACTIONS];
    /**
     * getting the strategy 
     * @param realizationWeight how often we reach this node
     * @return the total strategy choosen at frequency
     */
    public double[] getStrategy(double realizationWeight) {
        double normalizingSum = 0;
        // for each action set a value based on regret
        for (int a = 0; a < NUM_ACTIONS; a++) {
            strategy[a] = regretSum[a] > 0 ? regretSum[a] : 0;
            normalizingSum += strategy[a];
        }
        // for each action turn the value into a frequency
        for (int a = 0; a < NUM_ACTIONS; a++) {
            if (normalizingSum > 0)
                strategy[a] /= normalizingSum;
            else
                strategy[a] = 1.0 / NUM_ACTIONS;
            strategySum[a] += realizationWeight * strategy[a];
        }
        return strategy;
    }


    public double[] getAverageStrategy() {
        double[] avgStrategy = new double[NUM_ACTIONS];
        double normalizingSum = 0;
        for (int a = 0; a < NUM_ACTIONS; a++)
            normalizingSum += strategySum[a];
        for (int a = 0; a < NUM_ACTIONS; a++)
            if (normalizingSum > 0)
                avgStrategy[a] = strategySum[a] / normalizingSum;
            else
                avgStrategy[a] = 1.0 / NUM_ACTIONS;
        return avgStrategy;
    }
    /**
     * prints out the info set, and the corresponding average strategy at this decision node
     */
    public String toString() {
        return String.format("%10s: %s", infoSet, "[" + df.format(getAverageStrategy()[0]) + ", " + df.format(getAverageStrategy()[1]) + "]");
    }
    

}
