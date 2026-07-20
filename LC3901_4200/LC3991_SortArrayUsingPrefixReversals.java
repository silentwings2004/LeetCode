package LC3901_4200;
import java.util.*;
public class LC3991_SortArrayUsingPrefixReversals {
    /**
     * You are given an integer array nums of length n, where nums is a permutation of the integers in the range
     * [0, n - 1].
     *
     * You are also given an integer array pre, where each pre[i] is a valid prefix length.
     *
     * In one operation, you may choose any length x from pre and reverse the first x elements of nums.
     *
     * For example, applying a prefix reversal of length 3 on [4, 1, 2, 3] results in [2, 1, 4, 3].
     *
     * Return the minimum number of operations required to sort nums in ascending order. If it is impossible to sort
     * nums, return -1.
     *
     * Input: nums = [2,0,1], pre = [2,3]
     * Output: 2
     *
     * Input: nums = [1,0,2], pre = [1,3]
     * Output: -1
     *
     * Input: nums = [0,1], pre = [2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 8
     * 0 <= nums[i] <= n - 1
     * 1 <= pre.length <= n
     * 1 <= pre[i] <= n
     * nums is a permutation of integers from 0 to n - 1.
     * pre consists of unique integers.
     * @param nums
     * @param pre
     * @return
     */
    // time = O(m * n * n!), space = O(n * n!)
    public int sortArray(int[] nums, int[] pre) {
        int n = nums.length, m = pre.length;
        Queue<String> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int x : nums) sb.append(x);
        String s = sb.toString();
        q.offer(s);
        HashSet<String> set = new HashSet<>();
        set.add(s);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                char[] t = q.poll().toCharArray();
                if (check(t)) return step;
                for (int i = 0; i < m; i++) {
                    char[] w = generate(t, pre[i]);
                    if (set.add(get(w))) q.offer(new String(w));
                }
            }
            step++;
        }
        return -1;
    }

    private String get(char[] a) {
        StringBuilder sb = new StringBuilder();
        for (char x : a) sb.append(x);
        return sb.toString();
    }

    private char[] generate(char[] a, int len) {
        char[] w = a.clone();
        int l = 0, r = len - 1;
        reverse(w, l, r);
        return w;
    }

    private void reverse(char[] a, int l, int r) {
        while (l < r) {
            char t = a[l];
            a[l++] = a[r];
            a[r--] = t;
        }
    }

    private boolean check(char[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }
}