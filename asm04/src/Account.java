import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Account implements Serializable {
    private String customerId;
    private static final long serialVersionUID = 466465068185188439L;
    private String accountNumber;
    private double balance;
    private String accountType;
    private List<Transaction> transactions;
    private List<Transaction> transactionsAll;


    public Account(String customerId, String accountNumber, double balance) {
        this.customerId=customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(String customerId) {
        this.customerId = customerId;

    }

    public String getCustomerId() {
        return customerId;
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
        transactionsAll = TransactionDao.list();
        transactions =  transactionsAll.stream()
                .filter(account -> account.getAccountNumber().equals(getAccountNumber()))
                .collect(Collectors.toList());
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

    public void createTransaction(double amount, boolean status, String transactionType){
        transactionsAll = TransactionDao.list();
        Transaction transaction = new Transaction(accountNumber,amount,status,getDateTime(), transactionType);
        transactionsAll.add(transaction);
        TransactionDao.save(transactionsAll);
    }

    public void detailTransactions(){
        transactions = getTransactions();
        for(int i =0;i<transactions.size();i++){
            Transaction transaction = transactions.get(i);
            transaction.detailTransaction();
        }
    }

    public void input(){
        String accountNumber = AccountDao.setAccountNumber();
        this.accountNumber=accountNumber;
        double amount = AccountDao.setBalance();
        this.balance = amount;
        createTransaction(amount,true,"deposit");
    }

    public Customer getCustomer(){
        List<Customer> customersAll = CustomerDao.list();
        Customer customer;
        for(int i = 0; i<customersAll.size();i++){
             customer = customersAll.get(i);
             if(customer.getCustomerId().equals(getCustomerId())){
                 return customer;
             }
        }
        System.out.println("Khách hàng không tồn tại");
        return null;
    }

}
