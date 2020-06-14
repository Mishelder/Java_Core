package JavaMultithreading;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        for(int k=1;k<Integer.MAX_VALUE;k++){
            String spaces = spaces(k);
            Runnable printer = new RunnablePrinter(spaces+k,100);
            Thread thread = new Thread(printer);
            thread.start();
            Thread.sleep(100);
        }
    }

    public static String spaces(int count){
        StringBuilder spaces = new StringBuilder();
        for(int k =0;k<count;k++)
            spaces.append(" ");
        return spaces.toString();
    }
}
