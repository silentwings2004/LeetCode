package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: KthSmallestElementinaBST
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 230. Kth Smallest Element in a BST
 */
public class LC230_KthSmallestElementinaBST {
    /**
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     *
     *
     * Example 1:
     *
     * Input: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * Output: 1
     *
     * Example 2:
     *
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * Output: 3
     * Follow up:
     * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
     * How would you optimize the kthSmallest routine?
     *
     *
     *
     * Constraints:
     *
     * The number of elements of the BST is between 1 to 10^4.
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     * @param root
     * @param k
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    // S1: recursion
    // time = O(n), space = O(n)
    public int kthSmallest(TreeNode root, int k) {
        // corner case
        if (root == null) throw new IllegalArgumentException();
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.size() >= k ? list.get(k - 1) : list.get(list.size() - 1); // 注意第k小是k - 1，不是k
    }

    private void inorder(TreeNode root, List<Integer> list) {
        // base case
        if (root == null) list.add(root.val);

        if (root.left != null) inorder(root.left, list);
        list.add(root.val);
        if (root.right != null) inorder(root.right, list);
    }

    // S2: maxHeap
    public int kthSmallest2(TreeNode root, int k) {
        // corner case
        if (root == null) throw new IllegalArgumentException();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        helper(root, maxHeap, k);
        if (maxHeap.size() >= k) return maxHeap.peek();
        throw new IllegalStateException();
    }

    private void helper(TreeNode root, PriorityQueue<Integer> maxHeap, int k) {
        if (root.left != null) helper(root.left, maxHeap, k);
        if (maxHeap.size() < k) maxHeap.offer(root.val);
        else if (maxHeap.peek() > root.val) {
            maxHeap.poll();
            maxHeap.offer(root.val);
        }
        if (root.right != null) helper(root.right, maxHeap, k);
    }
}
