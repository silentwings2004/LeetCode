package LC2401_2700;

public class LC2516_TakeKofEachCharacterFromLeftandRight {
    /**
     * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each
     * minute, you may take either the leftmost character of s, or the rightmost character of s.
     *
     * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is
     * not possible to take k of each character.
     *
     * Input: s = "aabaaaacaabc", k = 2
     * Output: 8
     *
     * Input: s = "a", k = 1
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only the letters 'a', 'b', and 'c'.
     * 0 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // S1: sliding window
    // time = O(n), space = O(1)
    public int takeCharacters(String s, int k) {
        int[] a = new int[3], b = new int[3];
        for (char c : s.toCharArray()) a[c - 'a']++;
        for (int i = 0; i < 3; i++) {
            b[i] = a[i] - k;
            if (b[i] < 0) return -1;
        }

        int n = s.length(), res = 0;
        a = new int[3];
        for (int i = 0, j = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            a[u]++;
            while (a[u] > b[u]) a[s.charAt(j++) - 'a']--;
            res = Math.max(res, i - j + 1);
        }
        return n - res;
    }

    // S2: binary search
    // time = O(nlogn), space = O(n)
    public int takeCharacters2(String s, int k) {
        int n = s.length();
        int[][] presum = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - 'a';
            for (int j = 0; j < 3; j++) presum[i][j] = presum[i - 1][j];
            presum[i][u]++;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int l = 0, r = n - i;
            while (l < r) {
                int mid = l + r + 1 >> 1; // split into [0:i-1] and [j+1,n-1]
                if (check(presum, i, mid, n, k)) l = mid;
                else r = mid - 1;
            }
            if (check(presum, i, r, n, k)) res = Math.min(res, n - r);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private boolean check(int[][] presum, int i, int mid, int n, int k) {
        int j = i + mid - 1;
        int a1 = presum[i][0], b1 = presum[i][1], c1 = presum[i][2];
        int a2 = presum[n][0] - presum[j + 1][0], b2 = presum[n][1] - presum[j + 1][1], c2 = presum[n][2] - presum[j + 1][2];
        return a1 + a2 >= k && b1 + b2 >= k && c1 + c2 >= k;
    }
}
/**
 * 找中间的一个滑窗，使得a,b,c的数量不能超过一定的阈值
 * Instead of taking k of each character from left and right, we take at most count(c) - k of each character from middle.
 * For Example 1, s = "aabaaaacaabc" with k = 2:
 * we have freq = {'a': 8, 'b': 2, 'c': 2}
 * this converts to limits = freq - k = {'a': 6, 'b': 0, 'c': 0}
 * now the problem becomes "finding the longest substring where the occurrence of each character is within limits"
 * we can easily solve this new problem using sliding window
 */