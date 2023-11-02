import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Account> accounts;
    public Customer(String name, String customerId) {
        super(name, customerId);
        accounts = new ArrayList<Account>();
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public boolean isPremium(){
        for(int i=0;i<getAccounts().size();i++){
            Account checkAccount = getAccounts().get(i);
            if (checkAccount.isPremium()) {
                return true;
            }
        }return false;
    }
    public boolean addAccount(Account newAccount){
        Account existingAccount = findAccount(newAccount.getAccountNumber());
        if (existingAccount==null){
            this.accounts.add(new Account(newAccount.getAccountNumber(), newAccount.getBalance()));
            return true;
        }
        return false;
    }
    public double getBalance(){

        double balance = 0;
        for(int i=0;i<this.accounts.size();i++){
            Account account = this.accounts.get(i);
            balance += account.getBalance();
        }
        return balance;
    }
    public void displayInformation(){
        System.out.printf("%-13s|%9s |%9s |%,12.0fđ %n",this.getCustomerId(),this.getName(),(this.isPremium()?"Premium":"Normal"),this.getBalance());
        for(int i=0;i<accounts.size();i++){
            Account account = accounts.get(i);
            System.out.printf("%-6d%-7s|%,34.0fđ %n",(i+1),account.getAccountNumber(),account.getBalance());
        }
    }
    private Account findAccount(String accountNumber){
        for(int i=0;i<this.accounts.size();i++){
            Account checkAccount = this.accounts.get(i);
            if (checkAccount.getAccountNumber().equals(accountNumber)) {
            return checkAccount;
            }
        }
        return null;
    }
}
