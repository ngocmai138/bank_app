import java.util.UUID;

public class Bank {
    private final String id;

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }




}