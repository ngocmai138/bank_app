import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void isCustomerExisted() {
        DigitalBank bank = new DigitalBank();
        Customer customer = new Customer("FUNIX","001215000002");
        assertTrue(bank.isCustomerExisted("001111111111"));
        assertFalse(bank.isCustomerExisted(customer.getCustomerId()));
    }
}