package LC3601_3900;

public class LC3663_FindTheLeastFrequentDigit {
    /**
     * Given an integer n, find the digit that occurs least frequently in its decimal representation. If multiple digits
     * have the same frequency, choose the smallest digit.
     *
     * Return the chosen digit as an integer.
     *
     * The frequency of a digit x is the number of times it appears in the decimal representation of n.
     *
     * Input: n = 1553322
     * Output: 1
     *
     * Input: n = 723344511
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 2^31 - 1
     * @param n
     * @return
     */
    // time = O(logk), space = O(1)
    public int getLeastFrequentDigit(int n) {
        int[] cnt = new int[10];
        int mc = 11, res = -1;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            cnt[x]++;
        }
        for (int i = 0; i < 10; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] < mc) {
                mc = cnt[i];
                res = i;
            }
        }
        return res;
    }
}