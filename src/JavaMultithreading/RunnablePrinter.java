package JavaMultithreading;

public class RunnablePrinter implements Runnable {
    private String msg;
    private long sleepMillis;

    public RunnablePrinter(String msg, long sleepMillis) {
        this.msg = msg;
        this.sleepMillis = sleepMillis;
    }

    @Override
    public void run() {
        for(int k=0;k<10;k++){
            try{
                Thread.sleep(sleepMillis);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println(msg);
        }

    }
}
