package JavaMultithreading;

public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MicrobeRunnable()).start();
        Thread.sleep(100);
    }
}
