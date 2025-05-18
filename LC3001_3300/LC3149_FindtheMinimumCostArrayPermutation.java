package LC3001_3300;
import java.util.*;
public class LC3149_FindtheMinimumCostArrayPermutation {
    /**
     * You are given an array nums which is a permutation of [0, 1, 2, ..., n - 1]. The score of any permutation of
     * [0, 1, 2, ..., n - 1] named perm is defined as:
     *
     * score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... + |perm[n - 1] - nums[perm[0]]|
     *
     * Return the permutation perm which has the minimum possible score. If multiple permutations exist with this score,
     * return the one that is lexicographically smallest among them.
     *
     * Input: nums = [1,0,2]
     * Output: [0,1,2]
     *
     * Input: nums = [0,2,1]
     * Output: [0,2,1]
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 14
     * nums is a permutation of [0, 1, 2, ..., n - 1].
     * @param nums
     * @return
     */
    // time = O(n^2 * 2^n), space = O(n * 2^n)
    final int inf = 0x3f3f3f3f;
    public int[] findPermutation(int[] nums) {
        int n = nums.length;
        int[][] f = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) Arrays.fill(f[i], inf);
        for (int i = 0; i < n; i++) f[(1 << n) - 1][i] = Math.abs(i - nums[0]);
        int[][] from = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) Arrays.fill(from[i], -1);
        for (int i = (1 << n) - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0) continue;
                int res = inf;
                for (int k = 0; k < n; k++) {
                    if ((i >> k & 1) == 1) continue;
                    int r = f[i | 1 << k][k] + Math.abs(j - nums[k]);
                    if (r < res) {
                        res = r;
                        from[i][j] = k;
                    }
                }
                f[i][j] = res;
            }
        }
        int i = 0, j = 0;
        List<Integer> q = new ArrayList<>();
        while (j >= 0) {
            q.add(j);
            i |= 1 << j;
            j = from[i][j];
        }

        int[] res = new int[q.size()];
        for (int k = 0; k < q.size(); k++) res[k] = q.get(k);
        return res;
    }
}