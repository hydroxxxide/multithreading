import java.util.Random;

class BankOperation implements Runnable {
    private final Account[] accounts;
    private final Random random;

    public BankOperation(Account[] accounts) {
        this.accounts = accounts;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int accountIndex = random.nextInt(accounts.length);
            Account account = accounts[accountIndex];
            int amount = random.nextInt(100);
            boolean withdraw = random.nextBoolean();

            if (withdraw) {
                boolean success = false;
                while (!success) {
                    success = account.withdraw(amount);
                    if (!success) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                account.deposit(amount);
            }
        }
    }
}