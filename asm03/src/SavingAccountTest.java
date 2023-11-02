import static org.junit.Assert.*;

public class SavingAccountTest {
    SavingAccount account;
    @org.junit.Before
    public void setup(){
         account = new SavingAccount("1111111",7000000);
    }

    @org.junit.Test
    public void withdraw() {
        assertFalse(account.withdraw(6900000));
        assertTrue(account.withdraw(5000000));
    }

    @org.junit.Test
    public void isAccepted() {
        assertFalse(account.isAccepted(6000000));
        assertTrue(account.isAccepted(100000));
    }
}