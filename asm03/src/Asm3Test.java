import org.junit.Test;

import static org.junit.Assert.*;

public class Asm3Test {

    @Test
    public void validateAccount() {
        Asm3 asm3 = new Asm3();
        assertTrue(asm3.validateAccount("111111"));
        assertFalse(asm3.validateAccount("11111d"));
        assertFalse(asm3.validateAccount("22"));
    }
}