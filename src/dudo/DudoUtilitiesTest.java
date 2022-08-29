package dudo;
import org.junit.Test;

public class DudoUtilitiesTest {

    /**
     * testing claim history
     */
    @Test
    public void testClaimHistory() {

        boolean[] history = new boolean[12];
        for(int x = 0; x < 6; x++){
            history[x] = false;
        }
        for(int x = 6; x < 12; x++){
            history[x] = true;
        }
        String s = DudoUtilities.claimHistoryToString(history);
        System.out.println(s);

    }

    @Test
    public void testInfoSet() {
        boolean[] claimed = new boolean[12];
        for (int x = 0; x < 10; x++){
            claimed[x] = true;
        }
        int infoSet = DudoUtilities.infoSetToInteger(3, claimed);
        System.out.println(infoSet);

    }

    
}
