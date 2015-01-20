package chap04.ShopImitation;

/**
 * Created by user on 20.01.2015.
 */
public class Customer{
    private int id;
    private int purchases;

    public Customer() {
    }

    public Customer(int id, int purchases) {
        this.id = id;
        this.purchases = purchases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "[" + id + ']';
    }
}
