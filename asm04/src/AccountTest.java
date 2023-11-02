import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void isAccountPremium() {
        Account account = new Account("001111111111","123456",12000000);
        account.setAccountType("saving");
        assertTrue(account.isAccountPremium());
        Account account1 = new Account("001111111111","111111",5000000);
        account1.setAccountType("saving");
        assertFalse(account1.isAccountPremium());
    }
}