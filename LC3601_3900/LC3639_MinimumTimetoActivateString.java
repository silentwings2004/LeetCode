package LC3601_3900;

public class LC3639_MinimumTimetoActivateString {
    /**
     * You are given a string s of length n and an integer array order, where order is a permutation of the numbers in
     * the range [0, n - 1].
     *
     * Starting from time t = 0, replace the character at index order[t] in s with '*' at each time step.
     *
     * A substring is valid if it contains at least one '*'.
     *
     * A string is active if the total number of valid substrings is greater than or equal to k.
     *
     * Return the minimum time t at which the string s becomes active. If it is impossible, return -1.
     *
     * Note:
     *
     * A permutation is a rearrangement of all the elements of a set.
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abc", order = [1,0,2], k = 2
     * Output: 0
     *
     * Input: s = "cat", order = [0,2,1], k = 6
     * Output: 2
     *
     * Input: s = "xy", order = [0,1], k = 4
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * order.length == n
     * 0 <= order[i] <= n - 1
     * s consists of lowercase English letters.
     * order is a permutation of integers from 0 to n - 1.
     * 1 <= k <= 10^9
     * @param s
     * @param order
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(s, order, k, mid)) r = mid;
            else l = mid + 1;
        }
        return check(s, order, k, r) ? r : -1;
    }

    private boolean check(String s, int[] order, int k, int mid) {
        int n = s.length();
        long tot = 1L * n * (n + 1) / 2;
        boolean[] st = new boolean[n];
        for (int i = 0; i <= mid; i++) st[order[i]] = true;

        long invalid = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (st[i]) {
                invalid += 1L * j * (j + 1) / 2;
                j = 0;
            } else j++;
        }
        invalid += 1L * j * (j + 1) / 2;
        return tot - invalid >= k;
    }

    // S2: Double Linked List
    // time = O(n), space = O(n)
    public int minTime2(String s, int[] order, int k) {
        int n = s.length();
        long cnt = 1L * n * (n + 1) / 2;
        if (cnt < k) return -1;

        int[] l = new int[n + 1], r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = i - 1;
            r[i] = i + 1;
        }

        for (int i = n - 1;; i--) {
            int x = order[i], left = l[x], right = r[x];
            cnt -= 1L * (x - left) * (right - x);
            if (cnt < k) return i;
            if (left >= 0) r[left] = right;
            l[right] = left;
        }
    }
}