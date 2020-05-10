package masteringthreads.ch2_basics_of_threads.exercise_2_1;

import java.util.*;

public class ThreadPool {
    // Create a thread group field
    // Create a LinkedList field containing Runnable
    // Hint: Since LinkedList is not thread-safe, we need to synchronize it
    ThreadGroup threadGroup = new ThreadGroup("Grupa1");
    List<Runnable> runnableList = new LinkedList<>();

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
        for(int i = 0; i > poolSize; i++) {
            Worker worker = new Worker(threadGroup, "workerrr" + i);
            worker.start();
        }
    }

    private synchronized Runnable take() throws InterruptedException {
        // if the LinkedList is empty, we wait
        //
        // remove the first job from the LinkedList and return it
        while (runnableList.isEmpty()) {
            wait();
        }

        return runnableList.remove(0);
//        throw new UnsupportedOperationException("not implemented");
    }

    public synchronized void submit(Runnable job) {
        // Add the job to the LinkedList and notifyAll
        runnableList.add(job);
        notifyAll();
    }

    public synchronized int getRunQueueLength() {
        // return the length of the LinkedList
        // remember to also synchronize!
        return runnableList.size();
//        throw new UnsupportedOperationException("not implemented");
    }

    @SuppressWarnings("deprecation")
    public void shutdown() {
        // this should call stop() on the ThreadGroup.
        threadGroup.stop();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            // we run in an infinite loop:
            // remove the next job from the linked list using take()
            // we then call the run() method on the job
            while (true) {
                try {
                    Runnable job = take();
                    job.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
