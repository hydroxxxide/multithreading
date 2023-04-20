
public class Main {

    public static void main(String[] args) {
        Account[] accounts = {
                new Account(1, 1000),
                new Account(2, 2000),
                new Account(3, 3000)};

        Thread[] threads = new Thread[6];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new BankOperation(accounts));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Account account : accounts) {
            System.out.println("Account " + account.getNumber() + " balance: " + account.getBalance());
        }
    }
}
