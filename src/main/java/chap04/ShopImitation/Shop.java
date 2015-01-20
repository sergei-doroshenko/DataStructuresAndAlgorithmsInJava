package chap04.ShopImitation;

import chap04.Queue.QueueO;

import java.util.Random;

/**
 * Created by user on 20.01.2015.
 */
public class Shop extends Thread {
    private Cashier cashier1 = new Cashier(1, 10);
    private Cashier cashier2 = new Cashier(2, 10);
    private Cashier cashier3 = new Cashier(3, 10);

    public void addCustomer (Customer customer) {
        Cashier bestCashier = null;
        if (cashier1.getQueue().size() <= cashier2.getQueue().size() && cashier1.getQueue().size() <= cashier3.getQueue().size()) bestCashier = cashier1;
        if (cashier2.getQueue().size() <= cashier1.getQueue().size() && cashier2.getQueue().size() <= cashier3.getQueue().size()) bestCashier = cashier2;
        if (cashier3.getQueue().size() <= cashier1.getQueue().size() && cashier3.getQueue().size() <= cashier2.getQueue().size()) bestCashier = cashier3;

        QueueO<Customer> queue = bestCashier.getQueue();
        synchronized (queue) {
            while (queue.isFull()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            queue.insert(customer);
            queue.notifyAll();
        }

    }

    public void closeCashier(Thread t, Cashier cashier) {
        QueueO<Customer> queue = cashier.getQueue();
        synchronized (queue) {
            while (!queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            t.interrupt();
        }
    }

    @Override
    public void run() {
        Thread t1 = new Thread(cashier1);
        Thread t2 = new Thread(cashier2);
        Thread t3 = new Thread(cashier3);

        t1.start();
        t2.start();
        t3.start();

        for (int i = 0; i < 1000; i++) {
            addCustomer(new Customer(i, (new Random().nextInt(5) + 2) * 10));
        }

        closeCashier(t1, cashier1);
        closeCashier(t2, cashier2);
        closeCashier(t3, cashier3);

        System.out.println("Shop end.");
    }
}
