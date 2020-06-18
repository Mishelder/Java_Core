public class Test00 {

    public static void main(String[] args) {
        for (int k=1;k<=5;k++){
            new Thread(new TestRunnable(k)).start();
        }
    }


    private static class TestRunnable implements Runnable{
        private final int id;

        public TestRunnable(int id){
            this.id=id;
        }

        @Override
        public void run() {
            hello(id);
        }
    }

    public synchronized static void hello(int id){
        System.out.println(id+":* - wait - sleep");
        try{
            Test00.class.wait(1000);
        }catch (InterruptedException ignore){
        }
        System.out.println(id+": wait - * - sleep");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ignore){
        }
        System.out.println(id+": wait - sleep - *");
    }
}
