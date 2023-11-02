import java.util.List;

public class DigitalBank extends Bank {
    Customer customer;



    @Override
    public void addAccount(String customerId, Account account) {
        customer.addAccount(account);
    }

    public void addCustomer(String customerId, String customerName) {
        customer = new DigitalCustomer(customerName, customerId);
        super.addCustomer(customer);
    }

    public void withdraw(String customerId, String accountNumber, double amount) {
        List<Customer> customers = this.getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            customer = customers.get(i);
            if (customer.getCustomerId().equals(customerId)) {
                Account account = customer.getAccountByAccountNumber(accountNumber);
                if (account != null) {
                    if (account.getAccountType() == "saving") {
                        SavingAccount savingAccount = (SavingAccount) account;
                        savingAccount.withdraw(amount);
                    }
                    if (account.getAccountType() == "loan") {
                        LoanAccount loanAccount = (LoanAccount) account;
                        loanAccount.withdraw(amount);
                    }
                } else if (account==null) {

                    System.out.println("Tai khoan khong ton tai");
                }
            }
        }
    }
}
