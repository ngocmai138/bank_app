import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    Customer customer;
    Customer customer1;
    Account account;
    Account account1;

    @org.junit.Before
    public void setup() {
        DigitalBank bank = new DigitalBank();
        customer = bank.getCustomerById(CustomerDao.list(),"001111111111");
        account = customer.getAccountByAccountNumber("111111");
        account1 = new Account("001111111111","555555",90000);
        customer1 = new Customer("My Tam","001123456789");
    }

    @Test
    public void isAccountExit() {
        assertFalse(customer.isAccountExit(account1));
        assertTrue(customer.isAccountExit(account));
    }

    @Test
    public void getTotalAccountBalance() {
        double amount = customer.getTotalAccountBalance();
        customer.addAccount(account1);
        assertEquals(amount+ account1.getBalance(),customer.getTotalAccountBalance(),0);
    }

    @Test
    public void isCustomerPremium() {
        assertFalse(customer1.isCustomerPremium());
        assertTrue(customer.isCustomerPremium());
    }

    @Test
    public void getAccountByAccountNumber() {
        assertNull(customer.getAccountByAccountNumber("212121"));
        assertNotNull(customer.getAccountByAccountNumber("111111"));
    }
}