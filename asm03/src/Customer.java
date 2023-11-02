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
    public boolean isCustomerPremium(){
        for(int i=0;i<getAccounts().size();i++){
            Account checkAccount = getAccounts().get(i);
            if (checkAccount.isAccountPremium()) {
                return true;
            }
        }return false;
    }
    public void addAccount(Account newAccount){
        if (!isAccountExit(newAccount)){
            this.accounts.add(newAccount);
        }
    }
    public boolean isAccountExit(Account account){
        Account existingAccount = getAccountByAccountNumber(account.getAccountNumber());
        if (existingAccount==null){
            return false;
        }
        return true;
    }
    public double getTotalAccountBalance(){
        double balance = 0;
        for(int i=0;i<this.accounts.size();i++){
            Account account = this.accounts.get(i);
            if(account.getAccountType()=="saving"){
                balance += account.getBalance();
            }
        }
        return balance;
    }
    public void displayInformation(){
        System.out.printf("%-13s|%9s |%9s |%,19.0fđ %n",this.getCustomerId(),this.getName(),(this.isCustomerPremium()?"Premium":"Normal"),this.getTotalAccountBalance());

        for(int i=0;i<accounts.size();i++){
            Account account = accounts.get(i);
            System.out.printf("%-6d%-7s|%9S |%,30.0fđ %n",(i+1),account.getAccountNumber(),account.getAccountType(),account.getBalance());
        }
    }
    public Account getAccountByAccountNumber(String accountNumber){
        for(int i=0;i<this.accounts.size();i++){
            Account checkAccount = this.accounts.get(i);
            if (checkAccount.getAccountNumber().equals(accountNumber)) {
            return checkAccount;
            }
        }
        return null;
    }
}
