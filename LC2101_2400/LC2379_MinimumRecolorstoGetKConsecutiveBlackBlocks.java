package LC2101_2400;

public class LC2379_MinimumRecolorstoGetKConsecutiveBlackBlocks {
    /**
     * You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the color
     * of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.
     *
     * You are also given an integer k, which is the desired number of consecutive black blocks.
     *
     * In one operation, you can recolor a white block such that it becomes a black block.
     *
     * Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black
     * blocks.
     *
     * Input: blocks = "WBBWWBBWBW", k = 7
     * Output: 3
     *
     * Input: blocks = "WBWBBBW", k = 2
     * Output: 0
     *
     * Constraints:
     *
     * n == blocks.length
     * 1 <= n <= 100
     * blocks[i] is either 'W' or 'B'.
     * 1 <= k <= n
     * @param blocks
     * @param k
     * @return
     */
    // S1: sliding window
    // time = O(n), space = O(1)
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length(), res = n;
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            cnt += blocks.charAt(i) == 'W' ? 1 : 0;
            if (i - j + 1 == k) {
                res = Math.min(res, cnt);
                cnt -= blocks.charAt(j++) == 'W' ? 1 : 0;
            }
        }
        return res;
    }

    // S2: brute-force
    // time = O(n^2), space = O(1)
    public int minimumRecolors2(String blocks, int k) {
        int n = blocks.length(),res = n;
        for (int i = 0; i < n; i++) {
            int count = 0, sum = 0;
            boolean flag = false;
            for (int j = i; j < n; j++) {
                char c = blocks.charAt(j);
                if (c == 'W') count++;
                sum++;
                if (sum == k) {
                    flag = true;
                    break;
                }
            }
            if (flag) res = Math.min(res, count);
        }
        return res;
    }
}
/**
 * 前缀和
 * sliding window -> 动态维护白色块的数量
 */
