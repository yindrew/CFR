import org.junit.Test;

public class ColonelBlottoTest {
    ColonelBlotto cB = new ColonelBlotto();

    @Test
    public void testGetStrategy() {
        cB.train(1000000);
        double[] avgStrat = cB.getAverageStrategy(cB.strategySum);
        double[] oppAvgStrat = cB.getAverageStrategy(cB.oppStrategySum);

        System.out.println("strategy - playerOne - playerTwo");
        for(int x = 0; x < avgStrat.length; x++){
            System.out.println(cB.strategyList[x] + " - " + avgStrat[x] + " - " + oppAvgStrat[x]);
        }



    }
}
