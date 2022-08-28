import java.util.Arrays;
import java.util.Random;

public class LiarDieTrainer {
    static final int DOUBT = 0, ACCEPT = 1;
    static Random random = new Random();
    int sides;
    LiarDieNode[][] responseNodes;
    LiarDieNode[][] claimNodes;

    public LiarDieTrainer(int sides) {
        this.sides = sides;
        responseNodes = new LiarDieNode[sides][sides + 1];
        for (int myClaim = 0; myClaim <= sides; myClaim++) {
            for (int oppClaim = myClaim + 1; oppClaim <= sides; oppClaim++) {
                responseNodes[myClaim][oppClaim] = new LiarDieNode((oppClaim == 0 || oppClaim == sides) ? 1 : 2);
            }
        }
        claimNodes = new LiarDieNode[sides][sides + 1];
        for (int oppClaim = 0; oppClaim < sides; oppClaim++) {
            for (int roll = 1; roll <= sides; roll++) {
                claimNodes[oppClaim][roll] = new LiarDieNode(sides - oppClaim);
            }
        }

    }

    public void train(int iterations) {
        double[] regret = new double[sides];
        int[] rollAfterAcceptingClaim = new int[sides];
        for (int iter = 0; iter < iterations; iter++) {
            // initialize rolls and starting probabilities
            for (int i = 0; i < rollAfterAcceptingClaim.length; i++)
                rollAfterAcceptingClaim[i] = random.nextInt(sides) + 1;
            claimNodes[0][rollAfterAcceptingClaim[0]].pPlayer = 1;
            claimNodes[0][rollAfterAcceptingClaim[0]].pOpponent = 1;
            // accumulate realization weights forward
            for (int oppClaim = 0; oppClaim <= sides; oppClaim++) {
                // visit response nodes forward
                if (oppClaim > 0)
                    for (int myClaim = 0; myClaim < oppClaim; myClaim++) {
                        LiarDieNode node = responseNodes[myClaim][oppClaim];
                        double[] actionProb = node.getStrategy();
                        if (oppClaim < sides) {
                            LiarDieNode nextNode = claimNodes[oppClaim][rollAfterAcceptingClaim[oppClaim]];
                            nextNode.pPlayer += actionProb[1] * node.pPlayer;
                            nextNode.pOpponent += node.pOpponent;
                        }
                    }
                // visit claim nodes forward
                if (oppClaim < sides) {
                    LiarDieNode node = claimNodes[oppClaim][rollAfterAcceptingClaim[oppClaim]];
                    double[] actionProb = node.getStrategy();
                    for (int myClaim = oppClaim + 1; myClaim <= sides; myClaim++) {
                        double nextClaimProb = actionProb[myClaim - oppClaim - 1];
                        if (nextClaimProb > 0) {
                            LiarDieNode nextNode = responseNodes[oppClaim][myClaim];
                            nextNode.pPlayer += node.pOpponent;
                            nextNode.pOpponent += nextClaimProb * node.pPlayer;
                        }
                    }
                }
            }
            // Backpropogate utilities, adjusting regrets and strategies
            for (int oppClaim = sides; oppClaim >= 0; oppClaim--) {
                // visit claim nodes backwards
                if (oppClaim < sides) {
                    LiarDieNode node = claimNodes[oppClaim][rollAfterAcceptingClaim[oppClaim]];
                    double[] actionProb = node.strategy;
                    node.u = 0.0;
                    for (int myClaim = oppClaim + 1; myClaim <= sides; myClaim++) {
                        int actionIndex = myClaim - oppClaim - 1;
                        LiarDieNode nextNode = responseNodes[oppClaim][myClaim];
                        double childUtil = -nextNode.u;
                        regret[actionIndex] = childUtil;
                        node.u += actionProb[actionIndex] * childUtil;
                    }
                    for (int a = 0; a < actionProb.length; a++) {
                        regret[a] -= node.u;
                        node.regretSum[a] += node.pOpponent * regret[a];
                    }
                    node.pPlayer = node.pOpponent = 0;
                }

                // visit response nodes backwards
                if (oppClaim > 0)
                    for (int myClaim = 0; myClaim < oppClaim; myClaim++) {
                        LiarDieNode node = responseNodes[myClaim][oppClaim];
                        double[] actionProb = node.strategy;
                        node.u = 0.0;
                        double doubtUtil = (oppClaim > rollAfterAcceptingClaim[myClaim]) ? 1 : -1;
                        regret[DOUBT] = doubtUtil;
                        node.u += actionProb[DOUBT] * doubtUtil;
                        if (oppClaim < sides) {
                            LiarDieNode nextNode = claimNodes[oppClaim][rollAfterAcceptingClaim[oppClaim]];
                            regret[ACCEPT] = nextNode.u;
                            node.u += actionProb[ACCEPT] * nextNode.u;
                        }
                        for (int a = 0; a < actionProb.length; a++) {
                            regret[a] -= node.u;
                            node.regretSum[a] += node.pOpponent * regret[a];
                        }
                        node.pPlayer = node.pOpponent = 0;
                    }
            }

            // reset strategy sums after half of training
            if (iter == iterations / 2) {
                for (LiarDieNode[] nodes : responseNodes)
                    for (LiarDieNode node : nodes)
                        if (node != null)
                            for (int a = 0; a < node.strategySum.length; a++)
                                node.strategySum[a] = 0;
                for (LiarDieNode[] nodes : claimNodes)
                    for (LiarDieNode node : nodes)
                        if (node != null)
                            for (int a = 0; a < node.strategySum.length; a++)
                                node.strategySum[a] = 0;
            }

        }
        // print resulting strategy
        for (int initialRoll = 1; initialRoll <= sides; initialRoll++) {
            System.out.printf("Initial claim policy with roll %d: ", initialRoll);
            for (double prob : claimNodes[0][initialRoll].getAverageStrategy())
                System.out.printf("%.2f ", prob);
            System.out.println();
        }
        System.out.println("\nOld Claim\tNew Claim\tAction Probabilities");
        for (int myClaim = 0; myClaim <= sides; myClaim++)
            for (int oppClaim = myClaim + 1; oppClaim <= sides; oppClaim++)
                System.out.printf("\t%d\t%d\t%s\n", myClaim, oppClaim,
                        Arrays.toString(responseNodes[myClaim][oppClaim].getAverageStrategy()));
        System.out.println("\nOld Claim\tRoll\tAction Probabilities");
        for (int oppClaim = 0; oppClaim < sides; oppClaim++)
            for (int roll = 1; roll <= sides; roll++)
                System.out.printf("%d\t%d\t%s\n", oppClaim, roll,
                        Arrays.toString(claimNodes[oppClaim][roll].getAverageStrategy()));

    }

    public static void main(String[] args) {
        LiarDieTrainer trainer = new LiarDieTrainer(6);
        trainer.train(1000000);
        }

}