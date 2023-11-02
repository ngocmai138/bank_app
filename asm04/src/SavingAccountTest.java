import static org.junit.Assert.*;

public class SavingAccountTest {
    SavingAccount account;
    @org.junit.Before
    public void setup(){
         account = new SavingAccount("001111111111","1111111",7000000);
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

    @org.junit.Test
    public void transfer(){
        Account account1 = new SavingAccount("00122222222","222222",1000000);
        assertFalse(account.transfer(account1,6800000));
        assertTrue(account.transfer(account1,500000));
    }
}