import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }

    public void addCustomer(Customer newCustomer) {
        String customerId = newCustomer.getCustomerId();
        if (!isCustomerExisted(customerId)) {
            this.customers.add(newCustomer);
            System.out.println("Da them khach hang " + customerId + " vao danh sach");
        } else
            System.out.println("Khach hang nay da co trong he thong");
    }

    public void addAccount(String customerId, Account account) {
            for (Customer customer:customers){
                if(customer.getCustomerId().equals(customerId)){
                    customer.addAccount(account);
                    return;
                }
            }
    }


    public boolean isCustomerExisted(String customerId) {
        for (int i = 0; i < this.customers.size(); i++) {
            Customer checkCustomer = this.customers.get(i);
            if (checkCustomer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerById(String customerId) {
        for (int i = 0; i < getCustomers().size(); i++) {
            Customer customer = getCustomers().get(i);
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}