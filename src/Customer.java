import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    List<Account> accountAll;
    private List<Account> accounts;
    static final long serialVersionUID = -8614244905678224920L;

    public Customer(String name, String customerId) {
        super(name, customerId);
        accounts = getAccounts();
    }

    public List<Account> getAccounts() {
        accountAll = AccountDao.list();
        accounts = accountAll.stream()
                .filter(account -> account.getCustomerId().equals(getCustomerId()))
                .collect(Collectors.toList());
        return accounts;
    }

    public boolean isCustomerPremium() {
        for (int i = 0; i < getAccounts().size(); i++) {
            Account checkAccount = getAccounts().get(i);
            if (checkAccount.isAccountPremium()) {
                return true;
            }
        }
        return false;
    }

    public void displayTransactionInformation() {
        List<Account> accounts = getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            account.detailTransactions();
        }
    }

    public void addAccount(Account newAccount) {
        accountAll = AccountDao.list();
        if (!isAccountExit(newAccount)) {
            accountAll.add(newAccount);
            AccountDao.save(accountAll);
        }
    }

    public boolean isAccountExit(Account account) {
        Account existingAccount = getAccountByAccountNumber(account.getAccountNumber());
        if (existingAccount == null) {
            return false;
        }
        return true;
    }

    public double getTotalAccountBalance() {
        accounts = getAccounts();
        double balance = 0;
        for (int i = 0; i < this.accounts.size(); i++) {
            Account account = this.accounts.get(i);
            balance += account.getBalance();

//            if (account.getAccountType() == "saving") {
//                balance += account.getBalance();
//            }
        }
        return balance;
    }

    public void displayInformation() {
        System.out.printf("%-13s| %-25s | %-8s |%,20.0fđ %n", this.getCustomerId(), this.getName(), (this.isCustomerPremium() ? "Premium" : "Normal"), this.getTotalAccountBalance());
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.printf("\t%-2d%-7s|%-26S |%,31.0fđ %n", (i + 1), account.getAccountNumber(), account.getAccountType(), account.getBalance());
        }
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        accounts = getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            Account checkAccount = accounts.get(i);
            if (checkAccount.getAccountNumber().equals(accountNumber)) {
                return checkAccount;
            }
        }
        return null;
    }

    public Account getReceiveAccountByAccountName(String accountNumber) {
        accountAll = AccountDao.list();
        for (int i = 0; i < accountAll.size(); i++) {
            Account checkAccount = accountAll.get(i);
            if (checkAccount.getAccountNumber().equals(accountNumber)) {
                return checkAccount;
            }
        }
        return null;
    }

    public void transfer() {
        accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            String accountNumberSender = AccountDao.setAccountSenderNumber();
            String accountNumberReceiver = AccountDao.setAccountReceiverNumber();
            if (accountNumberReceiver == null) {
                System.out.println();
            } else if (accountNumberReceiver.equals(accountNumberSender)) {
                System.out.println("Tài khoản người gửi và người nhận không được trùng nhau ");
            } else {
                Account accountSender = getAccountByAccountNumber(accountNumberSender);
                Account accountReceiver = getReceiveAccountByAccountName(accountNumberReceiver);
                if (accountSender != null && accountReceiver != null) {
                    System.out.println("Gửi tiền đến tài khoản: " + accountNumberReceiver + " | " + accountReceiver.getCustomer().getName());
                    double amount = AccountDao.setAmount();
                    while (true) {
                        System.out.print("Xác nhận thực hiện chuyển " + amount + " từ tài khoản [" + accountNumberSender + "] đến tài khoản [" + accountNumberReceiver + "] (Y/N):");
                        String s1 = scanner.nextLine();
                        if (s1.equals("Y") || s1.equals("y")) {
                            SavingAccount savingAccountSender = (SavingAccount) accountSender;
                            SavingAccount savingAccountReceiver = (SavingAccount) accountReceiver;
                            savingAccountSender.transfer(savingAccountReceiver, amount);
                            AccountDao.update(savingAccountSender);
                            AccountDao.update(savingAccountReceiver);
                            break;
                        }
                        if (s1.equals("N") || s1.equals("n")) {
                            break;
                        }
                    }
                } else if (accountSender == null) {
                    System.out.println("Tài khoản người gửi không tồn tại");
                } else if (accountReceiver == null) {
                    System.out.println("Tài khoản người nhận không tồn tại");
                } else if (accountSender == null && accountReceiver == null) {
                    System.out.println("Tài khoản người gửi và người nhận không tồn tại");
                }
            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }


    }

    public void withdraw(Scanner scanner) {
        accounts = getAccounts();
        if (!accounts.isEmpty()) {

            String accountNumber = AccountDao.setAccountNumber();
            Account account = getAccountByAccountNumber(accountNumber);
            if (account != null) {
                System.out.println("Nhập số tiền rút: ");
                double amount = scanner.nextDouble();
                SavingAccount savingAccount = (SavingAccount) account;
                savingAccount.withdraw(amount);
                AccountDao.update(savingAccount);
            } else if (account == null) {
                System.out.println("Tài khoản không tồn tại.");
            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }
}
