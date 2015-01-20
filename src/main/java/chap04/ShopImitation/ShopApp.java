package chap04.ShopImitation;

/**
 * Created by user on 20.01.2015.
 */
public class ShopApp {
    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();
        shop.start();
        shop.join();
    }
}
