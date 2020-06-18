import java.util.Random;
import java.util.concurrent.Callable;

public class TestInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int i;
                for (i = 0; i < 1_000_000_000; i++) {
                    Math.sin(random.nextDouble());
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("i : " + i + "\nThread was interrupted");
                        break;
                    }
                }

            }
        });
        System.out.println("Thread started");
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        System.out.println("Thread finished");


    }


}
