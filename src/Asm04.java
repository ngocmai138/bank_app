import java.util.Scanner;

public class Asm04 {
    public static final String APP_NAME = "NGÂN HÀNG ĐIỆN TỬ";
    public static final String AUTHOR = "FX19868";
    public static final String VERSION = "4.0.0";
    public static Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    ;

    public static void main(String[] args) {

        firstMenu();
        function();

    }

    /**
     * Giao diện chương trình
     */
    static void firstMenu() {
        System.out.println("+----------+---------------------------------+----------+");
        System.out.println("| " + APP_NAME + " | " + AUTHOR + "@v" + VERSION + "                    |");
        System.out.println("+----------+---------------------------------+----------+");
        System.out.println("| 1. Xem danh sách khách hàng                           |");
        System.out.println("| 2. Nhập danh sách khách hàng                          |");
        System.out.println("| 3. Thêm tài khoản ATM                                 |");
        System.out.println("| 4. Chuyển tiền                                        |");
        System.out.println("| 5. Rút tiền                                           |");
        System.out.println("| 6. Tra cứu lịch sử giao dịch                          |");
        System.out.println("| 0. Thoát                                              |");
        System.out.println("+----------+---------------------------------+----------+");
        System.out.print("Chọn chức năng: ");
    }

    /**
     * Thực hiện chức năng được nhập vào từ bàn phím
     */
    private static void function() {
        String customerId;
        while (true) {
            String s = scanner.nextLine();
            try {
                switch (s) {
                    case "1":
                        activeBank.showCustomer();
                        firstMenu();
                        break;
                    case "2":
//input path:   vn.funix.fx19868.java\asm04\store\customers.txt
                        System.out.println("Nhập đường dẫn đến tệp:");
                        String inputPath = scanner.nextLine();
                        activeBank.addCustomers(inputPath);
                        firstMenu();
                        break;
                    case "3":
                        customerId = CustomerDao.setCustomerId();
                        activeBank.addSavingAccount(scanner, customerId);
                        firstMenu();
                        break;
                    case "4":
                        customerId = CustomerDao.setCustomerId();
                        activeBank.transfers(customerId);
                        firstMenu();
                        break;
                    case "5":
                        customerId = CustomerDao.setCustomerId();
                        activeBank.withdraw(scanner, customerId);
                        firstMenu();
                        break;
                    case "6":
                        customerId = CustomerDao.setCustomerId();
                        Customer customer = activeBank.getCustomerById(CustomerDao.list(), customerId);
                        customer.displayInformation();
                        customer.displayTransactionInformation();
                        firstMenu();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Vui lòng chọn chức năng từ 0-6");
                }
            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
                firstMenu();
            }
        }
    }


}
