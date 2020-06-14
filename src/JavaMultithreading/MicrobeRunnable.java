package JavaMultithreading;

public class MicrobeRunnable implements Runnable {

    private static int count;

    @Override
    public void run() {
        count++;
        for (int k = 0; k < 3; k++) {

          new Thread(new MicrobeRunnable()).start();
          try{
          Thread.sleep(100);
          }catch(InterruptedException e){
              throw new RuntimeException(e);
          }
            System.out.println("count: "+ count);
        }
    }
}
