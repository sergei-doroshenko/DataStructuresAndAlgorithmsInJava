package chap06.ProgrammProjects;

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
        if (start == items.length){ // index out of bounds
            return false;
        }

        int curr = items[start];       // save current item

        if (curr == weight){           // item match the weight
            inventory[start] = curr;   // took it in inventory
            return true;
        } else if (curr > weight || !find(start + 1, weight - curr)){ // try to take item, if not
            return find(start + 1, weight);                           // skip it and move forward
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
        System.out.println(arrToString("Items:     ", items));
    }

    public void printInventory() {
        System.out.println(arrToString("Inventory: ", inventory));
    }

    public static void main(String[] args) {
        KnapsackX knapsack = new KnapsackX(new int[]{11, 8, 7, 6, 5, 4}, 20);
        knapsack.printItems();

        System.out.println("=======================================================");
        knapsack.pack();
        knapsack.printInventory();
    }

}
