package chap04.ShopImitation;

import chap04.Queue.QueueO;

/**
 * Created by Sergei Doroshenko on 20.01.2015.
 */
public class Cashier implements Runnable{
    private int id;
    private int queueCapacity;
    private QueueO<Customer> queue;

    public Cashier() {
    }

    public Cashier(int id, int queueCapacity) {
        this.id = id;
        this.queueCapacity = queueCapacity;
        queue = new QueueO<>(queueCapacity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public QueueO<Customer> getQueue() {
        return queue;
    }

    public void setQueue(QueueO<Customer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        long handleTime = 0;
        while (!Thread.currentThread().isInterrupted()){

            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        System.out.println("Cashier " + id + " stop working.");
                    }
                }


                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println(this);
                    Customer customer = queue.remove();
                    handleTime = customer.getPurchases() * 1;

                    System.out.println(this);
                    queue.notifyAll();
                }
            }

            try {
                Thread.currentThread().sleep(handleTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "C" + id +" " + queue;
    }
}
