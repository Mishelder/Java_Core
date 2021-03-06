package Producer_Consumer_Semaphore;

public class Consumer implements Runnable {
    private final Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            queue.get();
        }
    }
}
