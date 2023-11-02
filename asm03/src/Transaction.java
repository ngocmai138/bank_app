import java.util.UUID;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;

    public Transaction(String accountNumber, double amount, boolean status, String time) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
        this.time =  time;
    }

    public void detailTransaction(){
        System.out.printf("[GD] %7s | %,18.0fđ | %13s %n",accountNumber,amount,time);
    }


}
