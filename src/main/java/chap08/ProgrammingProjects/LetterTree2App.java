package chap08.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class LetterTree2App {
    public static void main (String[] args) {
        LetterTree2 tree = new LetterTree2();
        tree.init(new char[]{'A', 'B', 'C', 'D', 'E', 'F'});
        tree.displayTree();
        tree.traverse(1);
        tree.traverse(2);
        tree.traverse(3);
    }
}
