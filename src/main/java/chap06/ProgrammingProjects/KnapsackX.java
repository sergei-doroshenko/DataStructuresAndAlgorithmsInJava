package chap06.ProgrammingProjects;

import libs.AppUtils;

/** The Knapsack Problem
 * Created by Sergei Doroshenko on 27.01.2015.
 */
public class KnapsackX {
    private int[] items;
    private int[] inventory;
    private int weight;

    public KnapsackX(int[] items, int weight) {
        this.items = items;
        this.weight = weight;
        this.inventory = new int[items.length];
    }

    public boolean pack (){
        return find(0, weight);
    }

    private boolean find(int start, int weight){

        /*  The base cases */
        if (start == items.length){ // index out of bounds
            return false;           // return false without recursion
        }

        int curr = items[start];       // save current item

        if (curr == weight){           // item match the weight
            inventory[start] = curr;   // take it in the inventory
            return true;
        /*
        * If item doesn't match to the weight. It may be in two cases:
        * 1. item more than remain weight
        * 2. item less than remain weight, but further execution of the program return false.
        * That's mean there is not items combinations that match remain weight.
        *  We check it recursively: !find(start + 1, weight - curr).
        * So in this case we doesn't include item in the inventory (e.g. inventory[start] = curr)
        */
        } else if (curr > weight || !find(start + 1, weight - curr)){ // try to take item, if not
            return find(start + 1, weight);                           // skip it (start + 1) and move forward
        }

        inventory[start] = curr;
        return true;
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
        System.out.println(AppUtils.arrToStr("Items:     ", items));
    }

    public void printInventory() {
        System.out.println(AppUtils.arrToStr("Inventory: ", inventory));
    }

    public static void main(String[] args) {
        KnapsackX knapsack = new KnapsackX(new int[]{18, 8, 7, 6, 5, 4}, 20);
        knapsack.printItems();

        System.out.println("=======================================================");
        knapsack.pack();
        knapsack.printInventory();
    }

}
