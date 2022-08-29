package dudo;
import java.util.Arrays;

import org.junit.Test;

public class DudoTrainerTest {
    DudoTrainer dTrainer = new DudoTrainer();

    @Test
    public void testRoll() {
        int[] testing = new int[2];
        dTrainer.rollDice(testing);
        System.out.println(Arrays.toString(testing));
    }
}
