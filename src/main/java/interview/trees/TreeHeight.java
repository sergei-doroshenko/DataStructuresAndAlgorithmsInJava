package interview.trees;

/**
 * Created by sergei on 2/26/16.
 */
public class TreeHeight {
    static class Node {
        Node left;
        Node right;

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    public static int calculateHeight(Node root) {
        return walkTree(root, 0);
    }

    public static int walkTree(Node node, int length) {
        int len = length + 1;

        if (node.left != null || node.right != null) {
            int left = 0, right = 0;
            if (node.left != null) {
                right = walkTree(node.left, len);
            }

            if (node.right != null) {
                left = walkTree(node.right, len);
            }

            if (left > right) return left;
            else return right;
        }

        return len;
    }

    public static void main(String[] args) {
        System.out.println("testCase0: " + testCase0()); // 3
        System.out.println("testCase1: " + testCase1()); // 4
        System.out.println("testCase2: " + testCase2()); // 6
        System.out.println("testCase3: " + testCase3()); // 1
        System.out.println("testCase4: " + testCase4()); // 2
    }

    public static int testCase0() {
        Node leaf1 = new Node(null, null);
        Node leaf2 = new Node(null, null);
        Node node1 = new Node(leaf1, leaf2);

        Node leaf3 = new Node(null, null);
        Node leaf4 = new Node(null, null);
        Node node2 = new Node(leaf3, leaf4);

        Node root = new Node(node1, node2);

        return calculateHeight(root); // 3
    }

    public static int testCase1() {
        Node leaf2 = new Node(null, null);
        Node leaf3 = new Node(null, null);
        Node node3 = new Node(leaf2, leaf3);

        Node leaf1 = new Node(null, null);
        Node node1 = new Node(leaf1, node3);

        Node node2 = new Node(null, null);

        Node root = new Node(node1, node2);

        return calculateHeight(root); // 6
    }

    public static int testCase2() {
        Node leaf1 = new Node(null, null);
        Node node4 = new Node(null, leaf1);
        Node node3 = new Node(null, node4);
        Node node2 = new Node(null, node3);
        Node node1 = new Node(null, node2);
        Node root = new Node(node1, null);

        return calculateHeight(root); // 4
    }

    public static int testCase3() {
        Node root = new Node(null, null);

        return calculateHeight(root); // 1
    }

    public static int testCase4() {
        Node leaf1 = new Node(null, null);
        Node root = new Node(null, leaf1);

        return calculateHeight(root); // 2
    }
}
