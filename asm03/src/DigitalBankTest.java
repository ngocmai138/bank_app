import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalBankTest {

    @Test
    public void getCustomerById() {
        DigitalBank digitalBank = new DigitalBank();
        Customer customer = new Customer("FUNIX","001215000001");
        digitalBank.addCustomer(customer);
        assertEquals(customer, digitalBank.getCustomerById("001215000001"));
        assertNull(digitalBank.getCustomerById("001111111111"));
    }
}