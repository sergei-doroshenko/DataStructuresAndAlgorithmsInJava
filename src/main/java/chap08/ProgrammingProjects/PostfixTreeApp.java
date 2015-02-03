package chap08.ProgrammingProjects;

/**
 * Created by Sergei Doroshenko on 30.01.2015.
 */
public class PostfixTreeApp {
    public static void main (String[] args) {
        PostfixTree tree = PostfixTree.init("345+*612+/-"); // 345+*612+/-

        tree.displayTree();
        tree.traverse(1);
        tree.traverse(2);
        tree.traverse(3);
    }
}
