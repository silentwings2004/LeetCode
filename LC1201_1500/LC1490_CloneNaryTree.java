package LC1201_1500;
import java.util.*;
public class LC1490_CloneNaryTree {
    /**
     * Given a root of an N-ary tree, return a deep copy (clone) of the tree.
     *
     * Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.
     *
     * class Node {
     *     public int val;
     *     public List<Node> children;
     * }
     * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated
     * by the null value (See examples).
     *
     * Input: root = [1,null,3,2,4,null,5,6]
     * Output: [1,null,3,2,4,null,5,6]
     *
     * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     *
     * Constraints:
     *
     * The depth of the n-ary tree is less than or equal to 1000.
     * The total number of nodes is between [0, 10^4].
     *
     * Follow up: Can your solution work for the graph problem?
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Node, Node> map;
    public Node cloneTree(Node root) {
        map = new HashMap<>();
        dfs(root);
        return map.get(root);
    }

    private void dfs(Node node) {
        if (node == null) return;

        map.put(node, new Node(node.val));
        for (Node next : node.children) {
            dfs(next);
            map.get(node).children.add(map.get(next));
        }
    }

    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
