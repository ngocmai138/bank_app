import java.util.List;

class MyThread extends Thread {
  Account account;
  Account editAccount;
    List<Account> updateAccount;
    public MyThread (Account account, Account editAccount,List<Account> updateAccount) {
        this.account=account;
        this.editAccount=editAccount;
        this.updateAccount=updateAccount;
    }

    public void run() {

        if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
            updateAccount.add(editAccount);
        } else {
            updateAccount.add(account);
        }

    }

}

