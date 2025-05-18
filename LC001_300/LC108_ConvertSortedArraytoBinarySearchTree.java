package LC001_300;
import java.util.*;
public class LC108_ConvertSortedArraytoBinarySearchTree {
    /**
     * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced
     * binary search tree.
     *
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs
     * by more than one.
     *
     * Input: nums = [-10,-3,0,5,9]
     * Output: [0,-3,9,-10,null,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * nums is sorted in a strictly increasing order.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) return null;

        int mid = l + r >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, l, mid - 1);
        root.right = dfs(nums, mid + 1, r);
        return root;
    }
}
/**
 * 实际考察的是线段树和平衡树的初始化
 * 高度 = log2(n+1)上取整
 */