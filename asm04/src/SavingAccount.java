import java.io.Serializable;

public class SavingAccount extends Account implements ReportService, Withdraw, Serializable,ITransfer, IReport {
    static long SAVING_ACCOUNT_MAX_WITHDRAW=5000000;
    static final long serialVersionUID = -31858459734955961L;
    public SavingAccount(String customerId,String accountNumber, double balance) {
        super(customerId,accountNumber,balance);
        setAccountType("saving");
        createTransaction(balance,true, "deposit");
    }

    public SavingAccount(String customerId) {
        super(customerId);
        setAccountType("saving");
        input();
    }

    @Override
    public void log(double amount) {
        System.out.println("+----------+---------------------------------+----------+");
        System.out.printf("%35S %n","bien lai giao dich saving");
        System.out.printf("%-15S%42s%n","ngay s/d:",getDateTime());
        System.out.printf("%-15S%42S%n","atm id:","digital-bank-atm 2022");
        System.out.printf("%-15S%42s%n","so tk:",getAccountNumber());
        System.out.printf("%-15S%,41.0fđ%n","so tien:",amount);
        System.out.printf("%-15S%,41.0fđ%n","so du:",getBalance());
        System.out.printf("%-15S%42s%n","phi + vat:","0đ");
        System.out.println("+----------+---------------------------------+----------+");
    }

    @Override
    public void log(double amount, String transactionType, String receiveAccount) {
        System.out.println("+----------+---------------------------------+----------+");
        System.out.printf("%35S %n","bien lai giao dich saving");
        System.out.printf("%-15S%42s%n","NGÀY GD:",getDateTime());
        System.out.printf("%-15S%42S%n","atm id:","digital-bank-atm 2022");
        System.out.printf("%-15S%42s%n","số tk:",getAccountNumber());
        System.out.printf("%-15S%42s%n","số tk nhận:",receiveAccount);
        System.out.printf("%-15S%,41.0fđ%n","số tiền chuyển:",amount);
        System.out.printf("%-15S%,41.0fđ%n","số dư:",getBalance());
        System.out.printf("%-15S%42s%n","phí + vat:","0đ");
        System.out.println("+----------+---------------------------------+----------+");
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance = getBalance()-amount;
        if(isAccepted(amount)){
            setBalance(newBalance);
            System.out.println("Rút tiền thành công, biên lai giao dịch: ");
            log(amount);
            createTransaction(-amount,true, "withdraw");
            return true;
        }
        System.out.println("Rút tiền không thành công");
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if((amount>50000) && (amount%10000==0)){
            if ((!isAccountPremium() && amount <=5000000) || isAccountPremium()){
                if(getBalance()-amount>=50000){
                    return true;
                }
            }
        }
        System.out.println("So tien khong hop le");
        return false;
    }

    @Override
    public boolean transfer(Account receiveAccount, double amount) {
        double newSenderBalance = getBalance()-amount;
        double newReceiverBalance = receiveAccount.getBalance()+amount;
        if(isAccepted(amount)){
            setBalance(newSenderBalance);
            receiveAccount.setBalance(newReceiverBalance);
            System.out.println("Chuyển tiền thành công, biên lai giao dịch: ");
            log(amount,"transfer", receiveAccount.getAccountNumber());
            createTransaction(-amount,true, "transfer");
            return true;
        }else
        System.out.println("Chuyển tiền không thành công");
        return false;
    }
}
