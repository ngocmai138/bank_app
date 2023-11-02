public class LoanAccount extends Account implements Withdraw, ReportService {
    static double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    static double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    static long LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private double fee;

    public LoanAccount(String accountNumber) {
        super(accountNumber);
        setAccountType("loan");
        setBalance(10000000);
        addTransaction(getBalance(),true);
    }

    @Override
    public boolean withdraw(double amount) {
        setFee(amount);
        double newBalance = getBalance() - amount - getFee();
        if (isAccepted(amount)) {
            setBalance(newBalance);
            log(amount);
            addTransaction(-amount,true);
            return true;
        }
        System.out.println("Rut tien khong thanh cong");
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        double newBalance = getBalance() - amount - fee;
        if (amount < LOAN_ACCOUNT_MAX_BALANCE && newBalance >= 50000) {
            return true;
        }
        return false;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double amount) {
        if (isAccountPremium()) {
            this.fee = amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        } else {
            this.fee = amount * LOAN_ACCOUNT_WITHDRAW_FEE;
        }
    }

    @Override
    public void log(double amount) {
        System.out.println("+----------+---------------------------------+----------+");
        System.out.printf("%35S %n","bien lai giao dich loan");
        System.out.printf("%-15S%35s%n","ngay s/d:",getDateTime());
        System.out.printf("%-15S%35S%n","atm id:","digital-bank-atm 2022");
        System.out.printf("%-15S%35s%n","so tk:",getAccountNumber());
        System.out.printf("%-15S%,34.0fđ%n","so tien:",amount);
        System.out.printf("%-15S%,34.0fđ%n","so du:",getBalance());
        System.out.printf("%-15S%,34.0fđ%n","phi + vat:",getFee());
        System.out.println("+----------+---------------------------------+----------+");
    }
}
