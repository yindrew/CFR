package preflop;
import org.junit.Test;

public class HandRankingTest {

    @Test
    public void testing() throws Exception {
        HandRanking handRanking = new HandRanking();
        for(int x = 0; x < 13; x++){
            for(int i = 0; i < 13; i++){
                System.out.print(handRanking.hand[i][x].handType() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testing2() throws Exception {
        HandRanking handRanking = new HandRanking();
        double[][] sol = handRanking.range(.2);
        // for(int i = 0; i < 13; i++){
        //     System.out.print(sol[i][i] + " ");
        // }


    }

    


    

}
