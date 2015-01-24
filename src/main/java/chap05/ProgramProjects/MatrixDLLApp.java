package chap05.ProgramProjects;

/**
 * Created by Sergei Doroshenko on 24.01.2015.
 */
public class MatrixDLLApp {
    public static void main (String[] args) {
        MatrixDLL<String> matrix = new MatrixDLL<>(3,3);
        /*matrix.insert("0,0", 0, 0);
        matrix.insert("1,0", 1, 0);
        matrix.insert("2,0", 2, 0);


        matrix.insert("0,1", 0, 1);
        matrix.insert("1,1", 1, 1);
        matrix.insert("2,1", 2, 1);


        matrix.insert("0,2", 0, 2);
        matrix.insert("1,2", 1, 2);
        matrix.insert("2,2", 2, 2);
       */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix.insert(i + "," + j, i, j);
            }
        }

        matrix.displayMatrix();
        System.out.println("---------------------------------------");
        matrix.insert("new", 2, 2);
        matrix.displayMatrix();
        System.out.println("---------------------------------------");
        matrix.delete(0, 2);
        matrix.displayMatrix();
        System.out.println("Item(2,2): " + matrix.get(2,2));
        System.out.println("Item(1,2): " + matrix.get(1,2));
        System.out.println("---------------------------------------");
        int[][] arr = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println("arr[0][0] = " + arr[0][0]);
        System.out.println("arr[0][1] = " + arr[0][1]);
        System.out.println("arr[1][0] = " + arr[1][0]);
    }
}
