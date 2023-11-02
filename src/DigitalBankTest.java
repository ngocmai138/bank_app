import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalBankTest {

    @Test
    public void getCustomerById() {
        DigitalBank digitalBank = new DigitalBank();
        assertNull(digitalBank.getCustomerById(CustomerDao.list(),"001215000002"));
        assertNotNull(digitalBank.getCustomerById(CustomerDao.list(),"001215000001"));
    }
}