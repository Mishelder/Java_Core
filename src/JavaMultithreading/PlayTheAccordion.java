package JavaMultithreading;

public class PlayTheAccordion {

    public static void main(String[] args)  {
       Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 10; k++) {
                    Runnable printerA = new RunnablePrinter("A  .",100);
                    Thread threadA = new Thread(printerA);
                    threadA.start();
                    Runnable printerB = new RunnablePrinter(".  B",99);
                    Thread threadB = new Thread(printerB);
                    threadB.start();
                    try {
                        threadA.join();
                        threadB.join();
                    }catch (InterruptedException ignore){}

                    System.out.println("-----");
                    Runnable printerC = new RunnablePrinter("   C",100);
                    Thread threadC = new Thread(printerC);
                    threadC.start();
                    try {
                        threadC.join();
                    }catch (InterruptedException ignore){}
                    System.out.println("-----");

                }
            }
        });

        thread.setDaemon(false);
        thread.start();

    }
}
