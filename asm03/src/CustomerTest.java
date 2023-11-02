import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    Customer customer;
    Account account;
    Account account1;

    @org.junit.Before
    public void setup() {
        customer = new Customer("FUNIX", "001215000001");
        account = new SavingAccount("111111", 8000000);
        account1 = new SavingAccount("222222", 12000000);
    }

    @Test
    public void isAccountExit() {
        assertFalse(customer.isAccountExit(account));
        customer.addAccount(account);
        assertTrue(customer.isAccountExit(account));
    }

    @Test
    public void getTotalAccountBalance() {
        customer.addAccount(account);
        customer.addAccount(account1);
        assertEquals(20000000,customer.getTotalAccountBalance(),0);
    }

    @Test
    public void isCustomerPremium() {
        customer.addAccount(account);
        assertFalse(customer.isCustomerPremium());
        customer.addAccount(account1);
        assertTrue(customer.isCustomerPremium());
    }

    @Test
    public void getAccountByAccountNumber() {
        assertNull(customer.getAccountByAccountNumber("111111"));
        customer.addAccount(account);
        assertNotNull(customer.getAccountByAccountNumber("111111"));
        assertEquals(account,customer.getAccountByAccountNumber("111111"));
    }
}