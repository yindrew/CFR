package rps;

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



}
