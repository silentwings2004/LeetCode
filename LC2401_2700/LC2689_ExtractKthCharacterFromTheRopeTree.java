package LC2401_2700;

public class LC2689_ExtractKthCharacterFromTheRopeTree {
    /**
     * You are given the root of a binary tree and an integer k. Besides the left and right children, every node of this
     * tree has two other properties, a string node.val containing only lowercase English letters (possibly empty) and
     * a non-negative integer node.len. There are two types of nodes in this tree:
     *
     * Leaf: These nodes have no children, node.len = 0, and node.val is some non-empty string.
     * Internal: These nodes have at least one child (also at most two children), node.len > 0, and node.val is an empty
     * string.
     * The tree described above is called a Rope binary tree. Now we define S[node] recursively as follows:
     *
     * If node is some leaf node, S[node] = node.val,
     * Otherwise if node is some internal node, S[node] = concat(S[node.left], S[node.right]).
     *  Return k-th character of the string S[root].
     *
     * Note: If s and p are two strings, concat(s, p) is a string obtained by concatenating p to s. For example,
     * concat("ab", "zz") = "abzz".
     *
     * Input: root = [10,4,"abcpoe","g","rta"], k = 6
     * Output: "b"
     *
     * Input: root = [12,6,6,"abc","efg","hij","klm"], k = 3
     * Output: "c"
     *
     * Input: root = ["ropetree"], k = 8
     * Output: "e"
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^3]
     * node.val contains only lowercase English letters
     * 0 <= node.val.length <= 50
     * 0 <= node.len <= 10^4
     * for leaf nodes, node.len = 0 and node.val is non-empty
     * for internal nodes, node.len > 0 and node.val is empty
     * 1 <= k <= S[root].length
     * @param root
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public char getKthCharacter(RopeTreeNode root, int k) {
        dfs(root);
        return root.val.charAt(k - 1);
    }

    private void dfs(RopeTreeNode node) {
        if (node.len == 0) return;

        if (node.left != null) dfs(node.left);
        if (node.right != null) dfs(node.right);
        String l = node.left != null ? node.left.val : "";
        String r = node.right != null ? node.right.val : "";
        node.val = l + r;
    }

    class RopeTreeNode {
        int len;
        String val;
        RopeTreeNode left, right;
        RopeTreeNode() {}
        RopeTreeNode(String val) {
            this.len = 0;
            this.val = val;
        }
        RopeTreeNode(int len) {
            this.len = len;
            this.val = "";
        }
        RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
            this.len  =len;
            this.val = "";
            this.left = left;
            this.right = right;
        }
    }
}
