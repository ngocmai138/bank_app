import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String APP_NAME = "NGAN HANG SO";
    public static final String AUTHOR = "FX19868";
    public static final String VERSION = "2.0.0";
    public static final Bank bank = new Bank();

    public static void main(String[] args) {
        firstMenu();
        function();
    }

    static void firstMenu() {
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| " + APP_NAME + " | " + AUTHOR + "@v" + VERSION + "                  |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| 1. Them khach hang                             |");
        System.out.println("| 2. Them tai khoan cho khach hang               |");
        System.out.println("| 3. Hien thi danh sach khach hang               |");
        System.out.println("| 4. Tim theo CCCD                               |");
        System.out.println("| 5. Tim theo ten khach hang                     |");
        System.out.println("| 0. Thoat                                       |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.print("Chuc nang:");
    }

    static void function() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s.equals("1")) {
                addNewCustomer();
                firstMenu();
            } else if (s.equals("2")) {
                addNewAccount();
                firstMenu();
            } else if (s.equals("3")) {
                displayCustomerInfo();
                firstMenu();
            } else if (s.equals("4")) {
                searchCustomerByCCCD();
                firstMenu();
            } else if (s.equals("5")) {
                searchCustomerByName();
                firstMenu();
            } else if (s.equals("0")) {
                break;
            } else System.out.println("Vui long chon chuc nang tu 0-5");
        }
    }

    static void addNewCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten khach hang: ");
        String name = sc.nextLine();
        String customerId = setCustomerId();
        Customer customer = new Customer(name, customerId);
        bank.addCustomer(customer);
    }

    static void addNewAccount() {
        boolean check = true;
        while (check) {
            String customerId = setCustomerId();
            String accountNumber = setAccountNumber();
            double balance = setBalance();
            Account account = new Account(accountNumber, balance);
            if (customerId != null && accountNumber != null) {
                bank.addAccount(customerId, account);
                check = false;
            }

        }
    }

    static void displayCustomerInfo() {
        List<Customer> customers = bank.getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            customer.displayInformation();
        }
    }

    static void searchCustomerByCCCD() {
        String customerId = setCustomerId();
        List<Customer> customers = bank.getCustomers();
        boolean existed = false;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getCustomerId().equals(customerId)) {
                customer.displayInformation();
                existed = true;
            }
        }
        if (existed == false) {
            System.out.println("Khach hang khong ton tai.");
        }
    }

    static void searchCustomerByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi ban nhap ten khach hang: ");
        String customerName = sc.nextLine();
        List<Customer> customers = bank.getCustomers();
        boolean existed = false;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getName().equals(customerName)) {
                customer.displayInformation();
                existed = true;
            }
        }
        if (existed == false) {
            System.out.println("Khach hang khong ton tai");
        }
    }

    static String setCustomerId() {
        Scanner sc = new Scanner(System.in);
        String customerId;
        boolean check = true;
        while (check) {
            System.out.print("Nhap CCCD khach hang: ");
            customerId = sc.nextLine();
            long v;
            try {
                v = Long.parseLong(customerId);
            } catch (Exception e) {
                System.out.println("So CCCD khong hop le. Vui long nhap lai.");
                continue;
            }
            if (customerId.length() == 12) {
                String sub = customerId.substring(0, 3);
                if (sub.matches("001|002|004|006|008|010|011|012|014|015|017|019|020|022|024|025|026|027|030|031|033|034|035|036|037|038|040|042|044|045|046|048|049|051|052|054|056|058|060|062|064|066|067|068|070|072|074|075|077|079|080|082|083|084|086|087|089|091|092|093|094|095|096")) {
                    check = false;
                    return customerId;
                } else {
                    System.out.println("So CCCD khong hop le. Vui long nhap lai.");
                }
            } else {
                System.out.println("So CCCD khong hop le. Vui long nhap lai.");
            }
        }
        return null;
    }

    static String setAccountNumber() {
        Scanner sc = new Scanner(System.in);
        String accountNumber;
        boolean check = true;
        while (check) {
            System.out.println("Nhap ma STK gom 6 chu so: ");
            accountNumber = sc.nextLine();
            long l;
            try {
                l = Long.parseLong(accountNumber);
            } catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai.");
                continue;
            }
            if (accountNumber.length() == 6) {
                return accountNumber;
            } else {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai.");
            }
        }
        return null;
    }
    static double setBalance(){
        Scanner sc = new Scanner(System.in);
        double balance=0;
        boolean check = true;
        while (check){
            System.out.println("Nhap so du:");
            balance = sc.nextDouble();
            if(balance>=50000){
                check=false;
                return balance;
            }else System.out.println("So du phai lon hon 50,000đ");
        }
        return balance;
    }
}

