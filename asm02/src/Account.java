public class Account {
    private String accountNumber;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean setAccountNumber(String accountNumber) {
        long v;
        try {
            v = Long.parseLong(accountNumber);
        } catch (Exception e) {
            System.out.println("So tai khoan khong hop le.");
            return false;
        }
        if (accountNumber.length() == 6) {
            this.accountNumber = accountNumber;
            return true;
        } else {
            System.out.println("So tai khoan phai la 6 chu so");
            return false;
        }
    }

    public boolean isPremium() {
        if (this.balance < 10000000) {
            return false;
        } else return true;
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String toString() {
        return this.accountNumber + " |          " + this.getBalance() + "đ";
    }
}
