import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account{
    private String accountNumber;
    private double balance;
    private String accountType;
    private List<Transaction> transactions;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions = new ArrayList<Transaction>();
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        transactions = new ArrayList<Transaction>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public String getDateTime(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sD = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String stringOfDate = sD.format(date);
        return stringOfDate;
    }

    public boolean isAccountPremium() {
        if (this.balance >= 10000000) {
            return true;
        } return false;
    }


    public String toString() {
        return this.accountNumber + " |          " + this.getBalance() + "đ";
    }

    public void addTransaction(double amount, boolean status){
        Transaction transaction = new Transaction(accountNumber,amount,status,getDateTime());
        transactions.add(transaction);
    }

    public void detailTransactions(){
        transactions = getTransactions();
        for(int i =0;i<transactions.size();i++){
            Transaction transaction = transactions.get(i);
            transaction.detailTransaction();
        }
    }

}
