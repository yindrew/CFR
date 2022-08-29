package kuhnPoker;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class KuhnNodeTest {
    KuhnNode node = new KuhnNode();

    @Test
    public void testGetStrategy() {
        double[] strat = node.getStrategy(1);
        System.out.println(Arrays.toString(strat));
        assertEquals(strat[0], .5, 0.0);

    }

    @Test
    public void testToString() {
        node.getStrategy(1);
        System.out.println(node.toString());

    }
}