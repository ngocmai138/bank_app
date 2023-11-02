import org.junit.Test;

import static org.junit.Assert.*;

public class LoanAccountTest {
    LoanAccount loanAccount;
    @org.junit.Before
    public void setup(){
        loanAccount = new LoanAccount("123456");
    }

    @Test
    public void withdraw() {
        assertFalse(loanAccount.withdraw(10000000));
        assertTrue(loanAccount.withdraw(350000));
    }

    @Test
    public void isAccepted() {
        assertFalse(loanAccount.isAccepted(6000000));
        assertTrue(loanAccount.isAccepted(100000));
    }

    @Test
    public void getFee() {
        loanAccount.setFee(500000);
        assertEquals(25000,loanAccount.getFee(),0);
    }
}