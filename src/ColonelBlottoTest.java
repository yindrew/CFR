import org.junit.Test;

import java.util.Arrays;

public class ColonelBlottoTest {
    ColonelBlotto cB = new ColonelBlotto();

    @Test
    public void testGetStrategy() {
        cB.train(100000);
        double[] avgStrat = cB.getAverageStrategy(cB.strategySum);

        for(int x = 0; x < avgStrat.length; x++){
            System.out.println(cB.strategyList[x] + ": " + avgStrat[x]);
        }



    }
}
