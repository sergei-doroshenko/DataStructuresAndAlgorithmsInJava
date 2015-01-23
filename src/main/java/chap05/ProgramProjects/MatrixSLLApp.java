package chap05.ProgramProjects;

/**
 * Created by user on 23.01.2015.
 */
public class MatrixSLLApp {
    public static void main (String[] args) {
        MatrixSLL<Integer> matrix = new MatrixSLL<>(7, 10);
        matrix.displayMatrix();
        matrix.insert(20, 1, 1);
        System.out.println("------------------------------------------");
        matrix.displayMatrix();

    }
}
