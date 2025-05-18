package LC1201_1500;
import java.util.*;
public class LC1485_CloneBinaryTreeWithRandomPointer {
    /**
     * A binary tree is given such that each node contains an additional random pointer which could point to any node
     * in the tree or null.
     *
     * Return a deep copy of the tree.
     *
     * The tree is represented in the same input/output way as normal binary trees where each node is represented as a
     * pair of [val, random_index] where:
     *
     * val: an integer representing Node.val
     * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not
     * point to any node.
     * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy. NodeCopy class
     * is just a clone of Node class with the same attributes and constructors.
     *
     * Input: root = [[1,null],null,[4,3],[7,0]]
     * Output: [[1,null],null,[4,3],[7,0]]
     *
     * Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
     * Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
     *
     * Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
     * Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 1000].
     * 1 <= Node.val <= 10^6
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Node, NodeCopy> map;
    public NodeCopy copyRandomBinaryTree(Node root) {
        map = new HashMap<>();
        return dfs(root);
    }

    private NodeCopy dfs(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        NodeCopy copy = new NodeCopy(node.val);
        map.put(node, copy);
        copy.left = dfs(node.left);
        copy.right = dfs(node.right);
        copy.random = dfs(node.random);
        return copy;
    }

    public class NodeCopy {
        int val;
        NodeCopy left, right, random;
        NodeCopy() {}
        NodeCopy(int val) {this.val = val;}
        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public class Node {
        int val;
        Node left, right, random;
        Node() {}
        Node(int val) {this.val = val;}
        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }
}