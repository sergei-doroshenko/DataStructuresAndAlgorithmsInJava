package chap08.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class LetterTreeApp {
    public static void main (String[] args) {
        LetterTree tree = LetterTree.getInstance(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'});
        tree.displayTree();
    }
}
