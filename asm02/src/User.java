public class User {
    private String name;
    private String customerId;
    public boolean setCustomerId(String customerId) {
        long v;
        try {
            v = Long.parseLong(customerId);
        } catch (Exception e) {
            System.out.println("So CCCD khong hop le.");
            return false;
        }
        if (customerId.length() == 12) {
            String sub = customerId.substring(0, 3);
            if (sub.matches("001|002|004|006|008|010|011|012|014|015|017|019|020|022|024|025|026|027|030|031|033|034|035|036|037|038|040|042|044|045|046|048|049|051|052|054|056|058|060|062|064|066|067|068|070|072|074|075|077|079|080|082|083|084|086|087|089|091|092|093|094|095|096")) {
                this.customerId = customerId;
                return true;
            } else return false;
        }else return false;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User(String name, String customerId) {
        setCustomerId(customerId);
        if (this.customerId.length()>1){
            this.name=name;
        }
    }
}
