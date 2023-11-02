import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {
    Customer customer;

    public void addSavingAccount(Scanner scanner, String customerId) {
        for (int i = 0; i < CustomerDao.list().size(); i++) {
            Customer customerTemp = CustomerDao.list().get(i);
            if (customerTemp.getCustomerId().equals(customerId)) {
                customer = customerTemp;
            }
        }
        Account account = AccountDao.addAccount(customerId);
        try {
            if (!isAccountExisted(AccountDao.list(), account)) {
                customer.addAccount(account);
                System.out.println("Tạo tài khoản thành công");
            } else {
                System.out.println("Tài khoản đã tồn tại");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCustomers(String fileName) {
        List<Customer> customers = CustomerDao.list();
        try (Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            scanner1.useDelimiter(" ");
            while (scanner1.hasNextLine()) {
                String customerId = scanner1.next();
                scanner1.skip(scanner1.delimiter());
                String customerName = scanner1.nextLine();
                if (!isCustomerExisted(customerId)) {
                    customers.add(new Customer(customerName, customerId));
                    System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách");
                } else
                    System.out.println("Khách hàng " + customerId + " đã tồn tại, thêm khách hàng không thành công");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tệp không tồn tại");
        }
        CustomerDao.save(customers);
    }

    public void withdraw(Scanner scanner, String customerId) {

        List<Customer> customers = CustomerDao.list();
        for (int i = 0; i < customers.size(); i++) {
            customer = customers.get(i);
            if (customer.getCustomerId().equals(customerId)) {
                customer.withdraw(scanner);

            }
        }
    }

    public boolean isCustomerExisted(String customerId) {
        return CustomerDao.list().stream().anyMatch(customer1 -> customer1.getCustomerId().equals(customerId));
    }

    public boolean isAccountExisted(List<Account> accounts, Account newAccount) {
        return accounts.stream().anyMatch(account -> account.getAccountNumber().equals(newAccount.getAccountNumber()));
    }

    public void transfers(String customerId) {
        List<Customer> customers = CustomerDao.list();
        for (int i = 0; i < customers.size(); i++) {
            customer = customers.get(i);
            if (customer.getCustomerId().equals(customerId)) {
                customer.transfer();
            }
        }
    }

    public void showCustomer(){
        CustomerDao.list().forEach(customer -> customer.displayInformation());
    }

    public Customer getCustomerById(List<Customer> customerList, String customerId) {
        customer = customerList.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findAny()
                .orElse(null);
        return customer;
    }
}
