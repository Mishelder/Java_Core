package Producer_Consumer_Semaphore;

public class Test {

    public static void main(String[] args) {
        Queue queue = new Queue();
        new Producer(queue);
        new Consumer(queue);
    }
}
