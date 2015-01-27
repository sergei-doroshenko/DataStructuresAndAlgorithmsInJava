package chap06.ProgrammProjects;

/**
 * Created by Sergei Doroshenko on 26.01.2015.
 */
public class BranchMaker {
    private int left;
    private int right;
    private char[] arr;
    private int arrInd;

    public BranchMaker(int left, int right) {
        this.left = left;
        this.right = right;
        this.arr = new char[(Calculator.lg(right + 1) + 1) * (right + 1)];
    }

    public BranchMaker make() {
        makeBranches(left, right);
        return this;
    }

    private void makeBranches (int left, int right) {
        int n = right - left + 1; // char number

        if (n == 1) {
            arr[arrInd + left] = 'X';
        } else {

            int mid = (left + right + 1) /2;

            for (int i = left; i < right + 1; i++) {
                if (i == mid) {
                    arr[arrInd + i] = 'X';
                } else {
                    arr[arrInd + i] = '-';
                }
            }

            int t = arrInd;
            arrInd += (this.right + 1);

            makeBranches(left, mid -1);
            arrInd = t + (this.right + 1);
            makeBranches(mid, right);
        }

    }

    public void display() {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && i % (right + 1) == 0) {
                System.out.println("");
            }
            System.out.print(arr[i]);

        }
    }

    public static void main (String[] args) {
        BranchMaker bm = new BranchMaker(0, 15);
        bm.make().display();
    }
}
