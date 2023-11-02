import java.util.List;
import java.util.Scanner;

public class Asm3 {
    public static final String APP_NAME = "NGAN HANG SO";
    public static final String AUTHOR = "FX19868";
    public static final String VERSION = "3.0.0";
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID,CUSTOMER_NAME);
        firstMenu();
        function();
    }
//    Giao dien chuong trinh
    static void firstMenu() {
        System.out.println("+----------+---------------------------------+----------+");
        System.out.println("| " + APP_NAME + " | " + AUTHOR + "@v" + VERSION + "                         |");
        System.out.println("+----------+---------------------------------+----------+");
        System.out.println("| 1. Thong tin khach hang                               |");
        System.out.println("| 2. Them tai khoan ATM                                 |");
        System.out.println("| 3. Them tai khoan tin dung                            |");
        System.out.println("| 4. Rut tien                                           |");
        System.out.println("| 5. Lich su giao dich                                  |");
        System.out.println("| 0. Thoat                                              |");
        System.out.println("+----------+---------------------------------+----------+");
        System.out.print("Chuc nang:");
    }
//    Thuc hien chuc nang duoc nhap vao tu ban phim
    private static void function(){
        while (true){
            String s = scanner.nextLine();
            if (s.equals("1")) {
                showCustomer();
                firstMenu();
            } else if (s.equals("2")) {
                addSavingAccount();
                firstMenu();
            } else if (s.equals("3")) {
                addLoanAccount();
                firstMenu();
            } else if (s.equals("4")) {
                withdraw();
                firstMenu();
            } else if (s.equals("5")) {
                displayTransactions();
                firstMenu();
            } else if (s.equals("0")) {
                break;
            } else System.out.println("Vui long chon chuc nang tu 0-5");
        }
    }
//    Hien thi thong tin khach hang
    private static void showCustomer(){
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if(customer != null)
            customer.displayInformation();
    }
//    Them tai khoan ATM
    private static void addSavingAccount(){
        boolean check = true;
        while (check) {
            String accountNumber = setAccountNumber();
            double balance = setBalance();
            Account account = new SavingAccount(accountNumber, balance);
            if (accountNumber != null) {
                activeBank.addAccount(CUSTOMER_ID, account);
                check = false;
            }

        }
    }
//    Them tai khoan tin dung
    private static void addLoanAccount(){
        boolean check = true;
        while (check) {
            String accountNumber = setAccountNumber();
            Account account = new LoanAccount(accountNumber);
            if (accountNumber != null) {
                activeBank.addAccount(CUSTOMER_ID, account);
                check = false;
            }

        }
    }
//    Thuc hien chuc nang rut tien
    private static void withdraw(){
        String accountNumber = setAccountNumber();
        System.out.println("So tien can rut: ");
        double amount = scanner.nextDouble();
        activeBank.withdraw(CUSTOMER_ID,accountNumber,amount);
    }
//    Hien thi danh sach giao dich
    private static void displayTransactions(){
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        customer.displayInformation();
        List<Account> accounts = customer.getAccounts();
        for(int i = 0;i<accounts.size();i++){
            Account account = accounts.get(i);
            account.detailTransactions();
        }
    }

// Truyen gia tri cho so tai khoan tu ban phim
    static String setAccountNumber() {
        String accountNumber;
        while (true) {
            System.out.println("Nhap ma STK gom 6 chu so: ");
            accountNumber = scanner.nextLine();
            if(validateAccount(accountNumber)){
                return accountNumber;
            }
        }
    }
// Kiem tra so tai khoan co hop le hay khong
    static boolean validateAccount(String accountNumber){
        long l;
        try {
            l = Long.parseLong(accountNumber);
        } catch (Exception e) {
            System.out.println("So tai khoan khong hop le. Vui long nhap lai.");
            return false;
        }
        if (accountNumber.length() == 6) {
            return true;
        } else {
            System.out.println("So tai khoan khong hop le. Vui long nhap lai.");
            return false;
        }
    }
//Kiem tra so du co hop le hay khong, neu hop le thi truyen gia tri vao balance
    static double setBalance(){
        double balance=0;
        boolean check = true;
        while (check){
            System.out.println("Nhap so du:");
            balance = scanner.nextDouble();
            if(balance>=50000){
                check=false;
                return balance;
            }else System.out.println("So du phai lon hon 50,000đ");
        }
        return balance;
    }
}

