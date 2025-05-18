package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ConvertSortedListtoBinarySearchTree
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 109. Convert Sorted List to Binary Search Tree
 */
public class LC109_ConvertSortedListtoBinarySearchTree {
    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted linked list: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     *
     * @param head
     * @return
     */
    // S1: Two Pointers --> slow / fast
    // time = O(nlogn), space = O(n)
    public TreeNode sortedListToBST(ListNode head) {

    }
}