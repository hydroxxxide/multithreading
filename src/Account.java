import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final int number;
    private int balance;
    private final ReentrantLock lock;

    public Account(int number, int balance) {
        this.number = number;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public int getNumber() {
        return number;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) { // пополнение
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(int amount) { // снятие
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
