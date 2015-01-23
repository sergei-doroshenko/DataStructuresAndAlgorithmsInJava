package chap05.ProgramProjects;

/**
 * Created by user on 23.01.2015.
 */
public class DequeDLLApp {
    public static void main (String[] args) {
        DequeDLL<Integer> dequeDLL = new DequeDLL<>();
        dequeDLL.insertRight(22);
        dequeDLL.insertRight(15);
        dequeDLL.insertRight(33);
        System.out.println(dequeDLL);

        dequeDLL.insertLeft(78);
        dequeDLL.insertLeft(17);
        dequeDLL.insertLeft(81);
        System.out.println(dequeDLL);

        System.out.println("Remove left: " + dequeDLL.removeLeft());
        System.out.println("Remove left: " + dequeDLL.removeRight());
        System.out.println(dequeDLL);

        System.out.println("Remove left: " + dequeDLL.removeLeft());
        System.out.println("Remove left: " + dequeDLL.removeRight());
        System.out.println(dequeDLL);

        System.out.println("Remove left: " + dequeDLL.removeLeft());
        System.out.println("Remove left: " + dequeDLL.removeRight());
        System.out.println(dequeDLL);
    }
}
