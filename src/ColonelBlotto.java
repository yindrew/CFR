import java.util.Random;


public class ColonelBlotto {
    public static final int NUM_ACTIONS = 18;
    
    public static final Random random = new Random();
    double[] regretSum = new double[NUM_ACTIONS],
            strategySum = new double[NUM_ACTIONS],
            strategy = new double[NUM_ACTIONS];
    String oppStrategy = "221";
    String[] strategyList = { "500", "410", "401", "320", "311", "302",
            "230", "221", "212", "203", "140", "131", "122", "113", "104",
            "050", "041", "032", "023", "014", "005" };
    
    public double[] getStrategy() {
        double normalizingSum = 0;
        for(int a = 0; a < NUM_ACTIONS; a++){
            strategy[a] = regretSum[a] > 0 ? regretSum[a] : 0;
        }

        for(int x = 0; x < NUM_ACTIONS; x++){
            if(normalizingSum > 0){
                strategy[x] /= normalizingSum;
            }
            else{
                strategy[x] = 1.0 / NUM_ACTIONS;
            }
            strategySum[x] += strategy[x];
        }
        
        return strategy;
    }

    public int getAction(double[] strategy){
        double r = random.nextDouble();
        int a = 0;
        double cumulativeProbability = 0;
        while (a < NUM_ACTIONS - 1) {
            if ( r < cumulativeProbability) {
                break;
            }
            a++;
        }
        return a;
    }
    

    public void train(int i) {
    }


}