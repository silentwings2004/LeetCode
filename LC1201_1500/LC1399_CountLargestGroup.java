package LC1201_1500;

public class LC1399_CountLargestGroup {
    /**
     * You are given an integer n.
     *
     * Each number from 1 to n is grouped according to the sum of its digits.
     *
     * Return the number of groups that have the largest size.
     *
     * Input: n = 13
     * Output: 4
     *
     * Input: n = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * @param n
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int countLargestGroup(int n) {
        int[] cnt = new int[40];
        int maxv = 0, res = 0;
        for (int i = 1; i <= n; i++) {
            int t = 0, x = i;
            while (x > 0) {
                t += x % 10;
                x /= 10;
            }
            cnt[t]++;
            maxv = Math.max(maxv, cnt[t]);
        }
        for (int i = 0; i < 40; i++) {
            if (cnt[i] == maxv) res++;
        }
        return res;
    }
}