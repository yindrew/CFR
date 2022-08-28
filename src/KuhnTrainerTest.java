import java.util.Arrays;

import org.junit.Test;

public class KuhnTrainerTest {
    KuhnTrainer trainer = new KuhnTrainer();

    @Test
    public void testShuffle() {
        int[] cards = {1, 2, 3};
        trainer.shuffle(cards);
        System.out.println(Arrays.toString(cards));
    }

}
