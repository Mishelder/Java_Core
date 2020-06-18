package TaskOfFivePhilosopher;

import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        new Philosopher("Аристотель",sem).start();
        new Philosopher("Платон",sem).start();
        new Philosopher("Сократ",sem).start();
        new Philosopher("Фалес",sem).start();
        new Philosopher("Пифагор",sem).start();
    }
}
