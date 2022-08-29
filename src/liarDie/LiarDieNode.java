package liarDie;

public class LiarDieNode {
    public double[] regretSum, strategy, strategySum;
    public double u, pPlayer, pOpponent;

    public LiarDieNode(int numActions) {
        regretSum = new double[numActions];
        strategy = new double[numActions];
        strategySum = new double[numActions];

    }

    public double[] getStrategy() {
        double normalizingSum = 0;
        for (int a = 0; a < strategy.length; a++) {
            strategy[a] = Math.max(regretSum[a], 0);
            normalizingSum += strategy[a];
        }
        for (int a = 0; a < strategy.length; a++) {
            if (normalizingSum > 0) {
                strategy[a] /= normalizingSum;
            } else {
                strategy[a] = 1.0 / strategy.length;
            }
        }
        for (int a = 0; a < strategy.length; a++) {
            strategySum[a] += pPlayer * strategy[a];
        }
        return strategy;
    }

    public double[] getAverageStrategy() {
        double normalizingSum = 0;
        for (int a = 0; a < strategySum.length; a++) {
            normalizingSum += strategySum[a];
        }
        for (int a = 0; a < strategySum.length; a++) {
            if (normalizingSum > 0) {
                strategySum[a] /= normalizingSum;
            } else {
                strategySum[a] = 1.0 / strategySum.length;
            }
        }
        return strategySum;
    }
}
