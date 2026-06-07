package LC2101_2400;
import java.util.*;
public class LC2196_CreateBinaryTreeFromDescriptions {
    /**
     * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that
     * parenti is the parent of childi in a binary tree of unique values. Furthermore,
     *
     * If isLefti == 1, then childi is the left child of parenti.
     * If isLefti == 0, then childi is the right child of parenti.
     * Construct the binary tree described by descriptions and return its root.
     *
     * The test cases will be generated such that the binary tree is valid.
     *
     *
     * @param descriptions
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for (int[] x : descriptions) {
            int parent = x[0], child = x[1], dir = x[2];
            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));
            if (dir == 1) map.get(parent).left = map.get(child);
            else map.get(parent).right = map.get(child);
            set.add(child);
        }

        TreeNode root = null;
        for (int[] x : descriptions) {
            if (set.contains(x[0])) continue;
            root = map.get(x[0]);
            break;
        }
        return root;
    }

    // S2
    // time = O(n), space = O(n)
    public TreeNode createBinaryTree2(int[][] descriptions) {
        TreeNode[] nodes = new TreeNode[100010];
        boolean[] hasParent = new boolean[100010];
        for (int[] x : descriptions) {
            int p = x[0], c = x[1], isLeft = x[2];
            if (nodes[p] == null) nodes[p] = new TreeNode(p);
            if (nodes[c] == null) nodes[c] = new TreeNode(c);
            if (isLeft == 1) nodes[p].left = nodes[c];
            else nodes[p].right = nodes[c];
            hasParent[x[1]] = true;
        }
        TreeNode root = null;
        for (int i = 0; i <= 100000; i++) {
            if (nodes[i] != null && !hasParent[i]) {
                root = nodes[i];
                break;
            }
        }
        return root;
    }
}
