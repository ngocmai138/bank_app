import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void isCustomerExisted() {
        Bank bank = new Bank();
        Customer customer = new Customer("FUNIX","001215000001");
        bank.addCustomer(customer);
        assertTrue(bank.isCustomerExisted("001215000001"));
        assertFalse(bank.isCustomerExisted("001111111111"));
    }
}