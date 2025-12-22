package LC3601_3900;
import java.util.*;
public class LC3781_MaximumScoreAfterBinarySwaps {
    /**
     * You are given an integer array nums of length n and a binary string s of the same length.
     *
     * Initially, your score is 0. Each index i where s[i] = '1' contributes nums[i] to the score.
     *
     * You may perform any number of operations (including zero). In one operation, you may choose an index i such that
     * 0 <= i < n - 1, where s[i] = '0', and s[i + 1] = '1', and swap these two characters.
     *
     * Return an integer denoting the maximum possible score you can achieve.
     *
     * Input: nums = [2,1,5,2,3], s = "01010"
     * Output: 7
     *
     * Input: nums = [4,7,2,9], s = "0000"
     * Output: 0
     *
     * Constraints:
     *
     * n == nums.length == s.length
     * 1 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * s[i] is either '0' or '1'
     * @param nums
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maximumScore(int[] nums, String s) {
        int n = nums.length;
        long res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < n; i++) {
            pq.offer(nums[i]);
            if (s.charAt(i) == '1') res += pq.poll();
        }
        return res;
    }
}