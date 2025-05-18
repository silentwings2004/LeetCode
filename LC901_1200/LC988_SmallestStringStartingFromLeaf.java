package LC901_1200;

public class LC988_SmallestStringStartingFromLeaf {
    /**
     * You are given the root of a binary tree where each node has a value in the range [0, 25] representing the
     * letters 'a' to 'z'.
     *
     * Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
     *
     * As a reminder, any shorter prefix of a string is lexicographically smaller.
     *
     * For example, "ab" is lexicographically smaller than "aba".
     * A leaf of a node is a node that has no children.
     *
     * Input: root = [0,1,2,3,4,3,4]
     * Output: "dba"
     *
     * Input: root = [25,1,3,1,3,0,2]
     * Output: "adz"
     *
     * Input: root = [2,2,1,null,1,0,null,0]
     * Output: "abc"
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 8500].
     * 0 <= Node.val <= 25
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    String res;
    StringBuilder path;
    public String smallestFromLeaf(TreeNode root) {
        res = "";
        path = new StringBuilder();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        path.append((char)(node.val + 'a'));

        if (node.left == null && node.right == null) {
            path.reverse();
            if (res.length() == 0 || res.compareTo(path.toString()) > 0) res = path.toString();
            path.reverse();
        } else {
            dfs(node.left);
            dfs(node.right);
        }
        path.setLength(path.length() - 1);
    }
}