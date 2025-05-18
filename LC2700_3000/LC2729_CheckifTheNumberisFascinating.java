package LC2700_3000;

public class LC2729_CheckifTheNumberisFascinating {
    /**
     * You are given an integer n that consists of exactly 3 digits.
     *
     * We call the number n fascinating if, after the following modification, the resulting number contains all the
     * digits from 1 to 9 exactly once and does not contain any 0's:
     *
     * Concatenate n with the numbers 2 * n and 3 * n.
     * Return true if n is fascinating, or false otherwise.
     *
     * Concatenating two numbers means joining them together. For example, the concatenation of 121 and 371 is 121371.
     *
     * Input: n = 192
     * Output: true
     *
     * Input: n = 100
     * Output: false
     *
     * Constraints:
     *
     * 100 <= n <= 999
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public boolean isFascinating(int n) {
        String s = String.valueOf(n) + String.valueOf(2 * n) + String.valueOf(3 * n);
        int[] cnt = new int[10];
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - '0';
            cnt[u]++;
            if (u == 0 || cnt[u] > 1) return false;
        }
        return true;
    }

    // S2
    // time = O(logn), space = O(1)
    public boolean isFascinating2(int n) {
        int[] cnt = new int[10];
        check(n, cnt);
        check(2 * n, cnt);
        check(3 * n, cnt);
        for (int i = 0; i < 10; i++) {
            if (i == 0 && cnt[i] > 0) return false;
            if (cnt[i] > 1) return false;
        }
        return true;
    }

    private void check(int n, int[] cnt) {
        while (n > 0) {
            cnt[n % 10]++;
            n /= 10;
        }
    }
}