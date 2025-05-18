package practice;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: HappyNumber
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: Happy Number
 */
public class LC202_HappyNumber {
    /**
     * Write an algorithm to determine if a number is "happy".
     *
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the
     * number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will
     * stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1
     * are happy numbers.
     *
     * Example:
     *
     * Input: 19
     * Output: true
     * Explanation:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * @param n
     * @return
     */
    // S1: HashSet
    // time = O(logn), space = O(logn)
    // 我们使用 HashSet 而不是向量、列表或数组的原因是因为我们反复检查其中是否存在某数字。检查数字是否在哈希集中需要 O(1)O(1) 的时间，
    // 而对于其他数据结构，则需要 O(n)O(n) 的时间。选择正确的数据结构是解决这些问题的关键部分。
    public boolean isHappy(int n) {
        // corner case
        if (n <= 0) return false;

        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = helper(n);
        }
        return n == 1;
    }

    private int helper(int n) {
        int sum = 0;
        while (n > 0) {
            int rem = n % 10;
            n /= 10;
            sum += rem * rem;
        }
        return sum;
    }

    // S2: slow - fast pointers
    // time = O(logn), space = O(1)
    // 意识到我们实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。
    // 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点。

    public boolean isHappy2(int n) {
        // corner case
        if (n <= 0) return false;

        int slow = n;
        int fast = helper(slow);
        while (fast != 1 && slow != fast) {
            slow = helper(slow);
            fast = helper(helper(fast));
        }
        return fast == 1;
    }
}
