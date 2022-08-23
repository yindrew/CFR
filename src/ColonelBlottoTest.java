import org.junit.Test;

import java.util.Arrays;

public class ColonelBlottoTest {
    ColonelBlotto cB = new ColonelBlotto();

    @Test
    public void testGetStrategy() {
        double[] buckets = cB.getStrategy();

        System.out.println(Arrays.toString(buckets));

    }
}
