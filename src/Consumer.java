public class Consumer implements Runnable {
    private final int id;
    private final SingleElementBuffer buffer;

    public Consumer(final int id, final SingleElementBuffer buffer){
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try{
                int elem = buffer.get();
                System.out.println(System.currentTimeMillis()+": "+elem+" consumed by "+id);
            }catch(InterruptedException ignore){
                return;
            }
        }
    }
}
