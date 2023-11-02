import java.util.List;
import java.util.Scanner;

public class CustomerDao {
    private final static String FILE_PATH = "vn.funix.fx19868.java\\asm04\\store\\customers.dat";

    public static void save(List<Customer> customers){
        BinaryFileService.writeFile(FILE_PATH, customers);
    }
    public static List<Customer> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static boolean isCustomerIdExit(String customerId) {
        boolean existed = false;
        for (int i = 0; i < list().size(); i++) {
            Customer customer = list().get(i);
            if (customer.getCustomerId().equals(customerId)) {
                existed = true;
            }
        }
        return existed;
    }

    public static String setCustomerId() {
        Scanner sc = new Scanner(System.in);
        String customerId;
        while (true) {
            System.out.print("Nhập mã số của khách hàng: ");
            customerId = sc.nextLine();
            long v;
            try {
                v = Long.parseLong(customerId);
            } catch (Exception e) {
                throw new CustomerIdNotValidException("Không tìm thấy khách hàng "+customerId+", tác vụ không thành công");
//                System.out.println("Không tìm thấy khách hàng "+customerId+", tác vụ không thành công");
            }
            if (customerId.length() == 12) {
                String sub = customerId.substring(0, 3);
                if (sub.matches("001|002|004|006|008|010|011|012|014|015|017|019|020|022|024|025|026|027|030|031|033|034|035|036|037|038|040|042|044|045|046|048|049|051|052|054|056|058|060|062|064|066|067|068|070|072|074|075|077|079|080|082|083|084|086|087|089|091|092|093|094|095|096")) {
                    if (isCustomerIdExit(customerId)) {
                        return customerId;
                    }else {
                        throw new CustomerIdNotValidException("Không tìm thấy khách hàng "+customerId+", tác vụ không thành công");
                    }
                }
            } else {
                throw new CustomerIdNotValidException("Không tìm thấy khách hàng "+customerId+", tác vụ không thành công");
            }
        }
    }
}
