import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestDeadLock {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        long finish = System.currentTimeMillis();
        System.out.println("time : " + (finish - start));
        runner.finished();
    }


    public static class Runner {
        private final Account acc1 = new Account();
        private final Account acc2 = new Account();

        private final Lock lock1 = new ReentrantLock();
        private final Lock lock2 = new ReentrantLock();

        public void firstThread() {
            Random random = new Random();
            lock1.lock();
            lock2.lock();
            for (int i = 0; i < 100; i++) {
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
            lock1.unlock();
            lock2.unlock();
        }

        public void secondThread() {
            Random random = new Random();
            lock1.lock();
            lock2.lock();
            for (int i = 0; i < 100; i++) {
                Account.transfer(acc2, acc1, random.nextInt(100));
            }
            lock1.unlock();
            lock2.unlock();
        }

        public void finished() {
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println("sum: " + (acc1.balance + acc2.balance));
        }

    }

    public static class Account {
        private int balance = 10000;

        public void deposit(int amount) {
            balance += amount;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public static void transfer(Account acc1, Account acc2, int amount) {
            acc1.withdraw(amount);
            acc2.deposit(amount);
        }

        @Override
        public String toString() {
            return "Account{" + "balance=" + balance + '}';
        }
    }

}
