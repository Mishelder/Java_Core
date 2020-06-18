package TaskOfFivePhilosopher;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private Semaphore sem;
    private boolean full = false;
    private String name;

    public Philosopher(String name, Semaphore sem){
        this.sem = sem;
        this.name= name;
    }

    @Override
    public void run() {
        try {
            if(!full){
                sem.acquire();
                System.out.println(name+" садится за стол");
                sleep(3000);
                full = true;
                System.out.println(name+" Выходит из-за стола");
                sem.release();

                sleep(3000);
            }
        }catch (InterruptedException ignore){}
    }
}
