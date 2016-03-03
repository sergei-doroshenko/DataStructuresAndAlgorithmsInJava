package interview._01_test;

import java.util.ArrayList;
import java.util.HashMap;

public class Dispatcher {
    private HashMap<Integer, Worker> workers = new HashMap<Integer, Worker>();

    public Iterable<Worker> getWorkers() {
        return this.workers.values();
    }

    public Worker acquireWorker(int id) {
        Worker w = this.workers.getOrDefault(id, null);
        if (w == null) {
            w = new Worker(id);
            this.workers.put(id, w);
        }

        return w;
    }

    public void releaseWorker(int id) {
        Worker w = this.workers.getOrDefault(id, null);
        if (w == null)
            throw new IllegalArgumentException();

        w.dispose();
        workers.remove(id);
    }

    public class Worker {
        private ArrayList<String> tasks = new ArrayList<String>();

        private int id;

        public int getId() {
            return this.id;
        }

        public Iterable<String> getTasks() {
            return this.tasks;
        }

        public Worker(int id) {
            this.id = id;
        }

        public void performTask(String task) {
            if (this.tasks == null)
                throw new IllegalStateException(this.getClass().getName());

            this.tasks.add(task);
        }

        public void dispose() {
            this.tasks = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Worker worker = (Worker) o;

            return id == worker.id;

        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public static void main(String[] args) {
        Dispatcher d = new Dispatcher();

        d.acquireWorker(1).performTask("Task11");
        d.acquireWorker(2).performTask("Task21");
        System.out.println(d.getWorkers());
        System.out.println(String.join(", ", d.acquireWorker(2).getTasks()));
        d.releaseWorker(2);
        d.acquireWorker(1).performTask("Task12");
        System.out.println(String.join(", ", d.acquireWorker(1).getTasks()));
        d.releaseWorker(1);
        System.out.println(d.getWorkers());
    }
}