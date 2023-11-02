import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String APP_NAME = "NGAN HANG SO";
    public static final String AUTHOR = "FX19868";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        selectSecurityLevelMenu();
        Scanner sc = new Scanner(System.in);
        Boolean checkD = true;
        while(checkD==true){
            String s = sc.nextLine();
            if(s.equals("1")){
                System.out.println("Đang khởi tạo chương trình ở mức bảo mật 1. Vui lòng chờ ...");
                verificationCodeBasic();
                checkD = false;
            } else if (s.equals("2")) {
                System.out.println("Đang khởi tạo chương trình ở mức bảo mật 2. Vui lòng chờ ...");
                verificationCodeAdvance();
                checkD = false;
            } else System.out.println("Vui lòng chọn 1 hoặc 2");
        }
    }

//    Xu ly ma bao mat
    static void verificationCodeAdvance() {
        Scanner sc = new Scanner(System.in);
        firstMenu();
        Boolean checkA = true;
        while (checkA == true) {
            String s = sc.nextLine();
            if (s.equals("1")) {
                System.out.print("Nhap ma xac thuc: ");
                boolean checkB = true;
                while (checkB == true) {
                    String l = getAlphaNumericString();
                    System.out.println(l);
                    String m = sc.next();
                    if (m.equals(l)) {
//              Xu ly sau khi nhap DUNG ma xac thuc
                        checkID();
                        checkB = false;
                    } else {
                        System.out.println("Ma xac thuc khong dung. Vui long thu lai.");

                    }
                }
                checkA = false;
            } else if (s.equals("0")) {
                break;
            } else System.out.println("just choose 1 or 0 please");
            continue;
        }

    }
    static void verificationCodeBasic(){
        Scanner sc = new Scanner(System.in);
        firstMenu();
        Boolean checkA = true;
        while (checkA == true) {
            String s = sc.nextLine();
            if (s.equals("1")) {
                System.out.print("Nhap ma xac thuc: ");
                boolean checkB = true;
                while (checkB == true) {
                    Random r = new Random();
                    String l = String.valueOf( r.nextInt((999-100)+1) + 100);
                    System.out.println(l);
                    String m = sc.next();
                    if (m.equals(l)) {
//              Xu ly sau khi nhap DUNG ma xac thuc
                        checkID();
                        checkB = false;
                    } else {
                        System.out.println("Ma xac thuc khong dung. Vui long thu lai.");

                    }
                }
                checkA = false;
            } else if (s.equals("0")) {
                break;
            } else System.out.println("Vui lòng chọn 1 hoặc 0");
            continue;
        }

    }
    static String getAlphaNumericString() {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = (int) (s.length() * Math.random());
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

//    Kiem tra thong tin nhap vao co hop le hay khong
    static void checkID() {
        System.out.print("Vui long nhap so CCCD: ");
        Scanner sc = new Scanner(System.in);
        boolean checkC = true;
        while (checkC) {
            String s = sc.nextLine();
            long v;
            if (!s.equals("No")) {
//                Kiem tra CCCD co bao gom chu cai hay khong
                try {
                    v = Long.parseLong(s);
                } catch (Exception e) {
                    System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
                    continue;
                }

                if (s.length() == 12) {
                    if (checkProvince(s)) {
//                  Ghi thong tin tu CCCD
                        Person p = insertDetails(s);
//                  Hien thi thong tin
                        getInfo(p);
                        checkC = false;
                    }else System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
                } else {
                    System.out.println("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat");
                }
            } else if (s.equals("No")) {
                break;
            }
        }
    }
    static boolean checkProvince(String s) {
        String sub = s.substring(0, 3);
        if (sub.matches("001|002|004|006|008|010|011|012|014|015|017|019|020|022|024|025|026|027|030|031|033|034|035|036|037|038|040|042|044|045|046|048|049|051|052|054|056|058|060|062|064|066|067|068|070|072|074|075|077|079|080|082|083|084|086|087|089|091|092|093|094|095|096")) {
            return true;
        }
        return false;
    }

//    Hien thi du lieu
    static void getInfo(Person p) {
        displayMenu();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.next();
            if (s.equals("1")) {
                System.out.println("Noi sinh: " + p.getProvince());
                displayMenu();
            } else if (s.equals("2")) {
                System.out.println("Gioi tinh: " + p.getGender() + " | " + p.getBirthOfYear());
                displayMenu();
            } else if (s.equals("3")) {
                System.out.println("So ngau nhien: " + p.getRandomNumber());
                displayMenu();
            } else if (s.equals("0")) {
                break;
            } else {
                System.out.println("Sai dinh dang, moi nhap lai");
            }
        }
    }
//    Nhap va tra cuu du lieu CCCD
    static Person insertDetails(String s) {
        String provinceNumber = s.substring(0, 3);
        String genderNumber = s.substring(3, 4);
        String birthOfYearNumber = s.substring(4, 6);
        String randomNumber = s.substring(6);


        String province;
        String gender;
        String birthOfYear;

//Gan Gioi province va Nam sinh
        switch (genderNumber) {
            case "0":
                birthOfYear = "19" + birthOfYearNumber;
                gender = "Nam";
                break;
            case "2":
                birthOfYear = "20" + birthOfYearNumber;
                gender = "Nam";
                break;
            case "4":
                birthOfYear = "21" + birthOfYearNumber;
                gender = "Nam";
                break;
            case "6":
                birthOfYear = "22" + birthOfYearNumber;
                gender = "Nam";
                break;
            case "8":
                birthOfYear = "23" + birthOfYearNumber;
                gender = "Nam";
                break;
            case "1":
                birthOfYear = "19" + birthOfYearNumber;
                gender = "Nu";
                break;
            case "3":
                birthOfYear = "20" + birthOfYearNumber;
                gender = "Nu";
                break;
            case "5":
                birthOfYear = "21" + birthOfYearNumber;
                gender = "Nu";
                break;
            case "7":
                birthOfYear = "22" + birthOfYearNumber;
                gender = "Nu";
                break;
            case "9":
            default:
                birthOfYear = "23" + birthOfYearNumber;
                gender = "Nu";
                break;

        }

//Gan Noi sinh
        switch (provinceNumber) {
            case "001":
                province = "Ha Noi";
                break;
            case "002":
                province = "Ha Giang";
                break;
            case "004":
                province = "Cao Bang";
                break;
            case "006":
                province = "Bac Kan";
                break;
            case "008":
                province = "Tuyen Quang";
                break;
            case "010":
                province = "Lao Cai";
                break;
            case "011":
                province = "Dien Bien";
                break;
            case "012":
                province = "Lai Chau";
                break;
            case "014":
                province = "Son La";
                break;
            case "015":
                province = "Yen Bai";
                break;
            case "017":
                province = "Hoa Binh";
                break;
            case "019":
                province = "Thai Nguyen";
                break;
            case "020":
                province = "Lang Son";
                break;
            case "022":
                province = "Quang Ninh";
                break;
            case "024":
                province = "Bac Giang";
                break;
            case "025":
                province = "Phu Tho";
                break;
            case "026":
                province = "Vinh Phuc";
                break;
            case "027":
                province = "Bac Ninh";
                break;
            case "030":
                province = "Hai Duong";
                break;
            case "031":
                province = "Hai Phong";
                break;
            case "033":
                province = "Hung Yen";
                break;
            case "034":
                province = "Thai Binh";
                break;
            case "035":
                province = "Ha Nam";
                break;
            case "036":
                province = "Nam Dinh";
                break;
            case "037":
                province = "Ninh Binh";
                break;
            case "038":
                province = "Thanh Hoa";
                break;
            case "040":
                province = "Nghe An";
                break;
            case "042":
                province = "Ha Tinh";
                break;
            case "044":
                province = "Quang Binh";
                break;
            case "045":
                province = "Quang Tri";
                break;
            case "046":
                province = "Thua Thien Hue";
                break;
            case "048":
                province = "Da Nang";
                break;
            case "049":
                province = "Quang Nam";
                break;
            case "051":
                province = "Quang Ngai";
                break;
            case "052":
                province = "Binh Dinh";
                break;
            case "054":
                province = "Phu Yen";
                break;
            case "056":
                province = "Khanh Hoa";
                break;
            case "058":
                province = "Ninh Thuan";
                break;
            case "060":
                province = "Binh Thuan";
                break;
            case "062":
                province = "Kon Tum";
                break;
            case "064":
                province = "Gia Lai";
                break;
            case "066":
                province = "Dak Lak";
                break;
            case "067":
                province = "Dak Nong";
                break;
            case "068":
                province = "Lam Dong";
                break;
            case "070":
                province = "Binh Phuoc";
                break;
            case "072":
                province = "Tay Ninh";
                break;
            case "074":
                province = "Binh Duong";
                break;
            case "075":
                province = "Dong Nai";
                break;
            case "077":
                province = "Ba Ria - Vung Tau";
                break;
            case "079":
                province = "Ho Chi Minh";
                break;
            case "080":
                province = "Long An";
                break;
            case "082":
                province = "Tien Giang";
                break;
            case "083":
                province = "Ben Tre";
                break;
            case "084":
                province = "Tra Vinh";
                break;
            case "086":
                province = "Vinh Long";
                break;
            case "087":
                province = "Dong Thap";
                break;
            case "089":
                province = "An Giang";
                break;
            case "091":
                province = "Kien Giang";
                break;
            case "092":
                province = "Can Tho";
                break;
            case "093":
                province = "Hau Giang";
                break;
            case "094":
                province = "Soc Trang";
                break;
            case "095":
                province = "Bac Lieu";
                break;
            case "096":
                province = "Ca Mau";
                break;
            default:
                province = "null";
                break;
        }

        Person p = new Person(province, birthOfYear, gender, randomNumber);

        return p;
    }

//    Giao dien
    static void displayMenu() {
        System.out.println("\t| 1. Kiem tra noi sinh");
        System.out.println("\t| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("\t| 3. Kiem tra so ngau hien");
        System.out.println("\t| 0. Thoat");
        System.out.print("Chuc nang: ");
    }
    static void selectSecurityLevelMenu(){
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| " + APP_NAME + " | " + AUTHOR + "@v" + VERSION + "                  |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| 1. Mức bảo mật bình thường (3 ký tự số)        |");
        System.out.println("| 2. Mức bảo mật cao (6 ký tự)                   |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.print("Mời chọn mức độ bảo mật:");
    }
    static void firstMenu() {

        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| " + APP_NAME + " | " + AUTHOR + "@v" + VERSION + "                  |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.println("| 1. Nhap CCCD                                   |");
        System.out.println("| 0. Thoat                                       |");
        System.out.println("+----------+--------------------------+----------+");
        System.out.print("Chuc nang:");
    }
}

class Person {
    String province;
    String birthOfYear;
    String gender;
    String randomNumber;

    public Person(String province, String birthOfYear, String gender, String randomNumber) {
        this.province = province;
        this.birthOfYear = birthOfYear;
        this.gender = gender;
        this.randomNumber = randomNumber;
    }

    public String getProvince() {
        return province;
    }

    public String getBirthOfYear() {
        return birthOfYear;
    }

    public String getGender() {
        return gender;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

}