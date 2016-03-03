package interview._01_test;

/**
 * Created by Сергей on 26.02.16.
 */
class TreeNode {
    private TreeNode leftChild, rightChild;

    public TreeNode(TreeNode leftChild, TreeNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public TreeNode getLeftChild() {
        return this.leftChild;
    }

    public TreeNode getRightChild() {
        return this.rightChild;
    }
}

public class TreeHeight {
    public static int calculateHeight(TreeNode root) {
        return getRecHeight(root, 0);
    }

    public static int getRecHeight(TreeNode node, int l) {

            int left = 0, right = 0;
            if (node.getLeftChild() != null) {
                return getRecHeight(node.getLeftChild(), l+1);
            }

            if (node.getRightChild() != null) {
                return getRecHeight(node.getRightChild(), l+1);
            }

        return l;
    }

    public static void main(String[] args) {
        TreeNode t01 = new TreeNode(null, null);
        TreeNode leaf1 = new TreeNode(null, t01);
        TreeNode leaf2 = new TreeNode(null, null);
        TreeNode node = new TreeNode(leaf1, null);
        TreeNode root = new TreeNode(node, leaf2);

        System.out.println(TreeHeight.calculateHeight(root));
    }
}
