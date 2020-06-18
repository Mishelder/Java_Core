package Producer_Consumer_Semaphore;

import java.util.concurrent.Semaphore;

public class Queue {

    private int buff;
    private final Semaphore semCon = new Semaphore(0);
    private final Semaphore semPro = new Semaphore(1);

    public void put(final int buff) {
        try {
            semPro.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.buff = buff;
        System.out.println("Buffer заполнен : " + buff);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semCon.release();
    }

    public int get() {
        try {
            semCon.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Buffer свободен : " + buff);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semPro.release();
        return this.buff;
    }
}
