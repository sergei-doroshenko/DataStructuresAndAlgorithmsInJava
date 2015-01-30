package chap06.ProgrammingProjects;

/**
 * Created by user on 27.01.2015.
 */
public class Backpack {
    private int[] items;
    private int[] inventory;
    private int weight;


    public Backpack(int[] items, int weight) {
        this.items = items;
        this.weight = weight;
        this.inventory = new int[items.length];
    }

    public void pack() {
        System.out.println(knapsack(weight, 0));
    }

    private void shift(int start) {
        int temp = items[start];
        for (int i = start; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
        items[items.length - 1] = temp;
    }

    /**
     * Recursive method
     * @param w - target weight
     * @param i - items array index
     */
    private int knapsack(int w, int i) {

        if (w == 0) {
            return 0;
        }

        if (i == items.length - 1) {
            return items[i];
        }

        //System.out.println("L: " + (w - items[i]));
        //System.out.printf("Target weight: %2d index: %d  item: %2d \n", w, i, items[i]);


        if (w - items[i] < 0) {
            shift(i);
            System.out.printf("%-15s Target weight: %2d index: %d item: %2d\n", arrToString("", inventory), w, i, items[i]);
            return  knapsack(w, i);
        } else {
            inventory[i] = items[i];
            System.out.printf("%-15s Target weight: %2d index: %d item: %2d\n", arrToString("", inventory), w, i, items[i]);
            return knapsack(w - items[i], ++i);
        }

    }

    public void myPack() {
        for (int i = 0; i < items.length; i++) {
            int w = items[i];

        }
    }

    private String arrToString(String head, int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sb.append(arr[i]);
                if (i < arr.length - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public void printItems() {
        System.out.println(arrToString("Items: ", items));
    }

    public void printInventory() {
        System.out.println(arrToString("", inventory));
    }


    public static void main(String[] args) {
        Backpack backpack = new Backpack(new int[] {11, 8, 7, 6, 5}, 20);
        backpack.printItems();
        System.out.println("===========================================================");
        backpack.pack();
    }
}
