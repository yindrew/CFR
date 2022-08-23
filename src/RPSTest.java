import java.util.Arrays;

import org.junit.Test;

public class RPSTest {
    RPS rps = new RPS();

    @Test
    public void testGetStrategy() {
        double[] strat = rps.getStrategy();
        for (int x = 0; x < strat.length; x++){
            System.out.println(strat[x]);
        } 
    }


    @Test
    public void testGetAction() {
        rps.strategy = rps.getStrategy();
        System.out.println(rps.getAction(rps.strategy));   

    }

    @Test
    public void testAll() {
        rps.train(1000000);
        double[] avgStrat = rps.getAverageStrategy(rps.strategySum);
        double[] oppAvgStrat = rps.getAverageStrategy(rps.oppStrategySum);

       System.out.println(Arrays.toString(avgStrat));
       System.out.println(Arrays.toString(oppAvgStrat));

    }



}
