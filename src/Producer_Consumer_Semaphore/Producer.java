package Producer_Consumer_Semaphore;

public class Producer implements Runnable {
    private final Queue queue;

    public Producer(final Queue queue) {
        this.queue = queue;
        new Thread(this).start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++)
            queue.put(i);
    }
}
