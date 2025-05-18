package LC2401_2700;
import java.util.*;
public class LC2611_MiceandCheese {
    /**
     * There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.
     *
     * A point of the cheese with index i (0-indexed) is:
     *
     * reward1[i] if the first mouse eats it.
     * reward2[i] if the second mouse eats it.
     * You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.
     *
     * Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.
     *
     * Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
     * Output: 15
     *
     * Input: reward1 = [1,1], reward2 = [1,1], k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == reward1.length == reward2.length <= 10^5
     * 1 <= reward1[i], reward2[i] <= 1000
     * 0 <= k <= n
     * @param reward1
     * @param reward2
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{reward1[i], reward2[i]};
        Arrays.sort(a, (o1, o2) -> (o2[0] - o2[1]) - (o1[0] - o1[1]));

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i < k) res += a[i][0];
            else res += a[i][1];
        }
        return res;
    }
}