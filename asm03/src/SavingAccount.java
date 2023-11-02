public class SavingAccount extends Account implements ReportService, Withdraw {
    static long SAVING_ACCOUNT_MAX_WITHDRAW=5000000;
    public SavingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        setAccountType("saving");
        addTransaction(balance,true);
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
    public boolean withdraw(double amount) {
        double newBalance = getBalance()-amount;
        if(isAccepted(amount)){
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
}
