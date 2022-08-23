import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class AppTest {
    @Test
    public void testAdd(){
        assertEquals(2, 1 + 1);

    }

    @Test
    public void testSubtract() {
        assertFalse("failed", 5 - 1 == 3);
    }
}
