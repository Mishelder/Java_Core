public class Producer implements Runnable {
    private int startValue;
    private final int period;
    private final SingleElementBuffer buffer;

    public Producer(int startValue,int period, SingleElementBuffer buffer){
        this.buffer = buffer;
        this.period = period;
        this.startValue = startValue;
    }

    @Override
    public void run() {
        while(true){
            try{
                System.out.println(System.currentTimeMillis()+": "+startValue+" produced");
                buffer.put(startValue++);
                Thread.sleep(period);
            }catch (InterruptedException ignore){
                return;
            }
        }
    }
}
