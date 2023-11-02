import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDao {
    public static Scanner scanner = new Scanner(System.in);
    private final static String FILE_PATH = "vn.funix.fx19868.java\\asm04\\store\\accounts.dat";

    public static void save(List<Account> accounts) {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    public static void update(Account editAccount) {
        List<Account> accounts = list();
        boolean hasExit = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
        List<Account> updateAccount;
//        MyThread temp;
        if (!hasExit) {
            updateAccount = new ArrayList<>(accounts);
            updateAccount.add(editAccount);
        } else {
            updateAccount = new ArrayList<>();
            for (Account account : accounts) {
                Thread temp = new MyThread(account, editAccount, updateAccount);
                temp.start();
                while (temp.isAlive()) {
                }
            }
        }
        save(updateAccount);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static Account addAccount(String customerId) {
        Account account = new SavingAccount(customerId);
        list().add(account);
        save(list());
        return account;
    }


    static String setAccountNumber() {
        String accountNumber;
        while (true) {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            accountNumber = scanner.nextLine();
            if (validateAccount(accountNumber)) {
                return accountNumber;
            }
        }
    }

    static String setAccountSenderNumber() {
        String accountNumber;
        while (true) {
            System.out.print("Nhập số tài khoản: ");
            accountNumber = scanner.nextLine();
            if (validateAccount(accountNumber)) {
                return accountNumber;
            }
        }
    }

    static String setAccountReceiverNumber() {
        String accountNumber;
        while (true) {
            System.out.print("Nhập số tài khoản nhận (exit để thoát): ");
            accountNumber = scanner.nextLine();
            if (accountNumber.equals("exit")) {
                return null;
            }
            if (validateAccount(accountNumber)) {
                return accountNumber;
            }
        }

    }


    static double setBalance() {
        double balance = 0;
        while (true) {
            System.out.println("Nhập số dư:");
            balance = scanner.nextDouble();
            if (balance >= 50000) {
                return balance;
            } else System.out.println("Số dư phải lớn hơn 50,000đ");
        }
    }

    static double setAmount() {
        double balance = 0;
        while (true) {
            System.out.println("Nhập số tiền chuyển:");
            balance = scanner.nextDouble();
            if (balance >= 50000) {
                return balance;
            } else System.out.println("Số dư phải lớn hơn 50,000đ");
        }
    }

    static boolean validateAccount(String accountNumber) {
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


}
