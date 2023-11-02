import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "vn.funix.fx19868.java\\asm04\\store\\transactions.dat";
    public static void save(List<Transaction> transactions){
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }
    public static List<Transaction> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }
}
