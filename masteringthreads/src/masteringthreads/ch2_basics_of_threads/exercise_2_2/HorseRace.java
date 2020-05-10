package masteringthreads.ch2_basics_of_threads.exercise_2_2;

public class HorseRace {
    public static void main(String... args) throws InterruptedException {
        Runnable race = () -> {
            // todo: wait on a common lock object, e.g.
            try {
                synchronized (HorseRace.class) {
                    HorseRace.class.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("STARTED: " + Thread.currentThread());
            // todo: run through a long loop or do some other exertion
            for (int i = 0; i < 100000; i ++) {
                for (int j = 0; j < 100000; j ++) {
                    for (int k = 0; k < 100000; k ++) {
                        int z = 0 + i;
                    }
                }
            }
            System.out.println("FINISHED: " + Thread.currentThread());
        };
        // todo: create a new thread for each priority (in a for loop) and start
        // it

        for (int i = Thread.MIN_PRIORITY; i < Thread.MAX_PRIORITY; i++) {
            Thread th = new Thread(race, "thread " + i);
            th.setPriority(i);
            th.start();
        }

        System.out.println("on your marks ...");
        Thread.sleep(1000);
        System.out.println("get set ...");
        Thread.sleep(1000);
        System.out.println("GO!!!");

        synchronized (HorseRace.class) {
            HorseRace.class.notifyAll();
        }
        // todo: notify all threads waiting on the common lock object
    }
}
