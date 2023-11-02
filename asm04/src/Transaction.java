import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private static final long serialVersionUID = -122478833250580846L;
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    private String transactionType;


    public Transaction(String accountNumber, double amount, boolean status, String time, String transactionType) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
        this.time =  time;
        this.transactionType = transactionType;
    }

    public void detailTransaction(){
        System.out.printf("[GD] %7s | %-8S | %,-13.0fđ | %13s %n",accountNumber, transactionType,amount,time);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
