package LC3601_3900;
import java.util.*;
public class LC3896_MinimumOperationstoTransformArrayintoAlternatingPrime {
    /**
     * You are given an integer array nums.
     *
     * An array is considered alternating prime if:
     *
     * Elements at even indices (0-based) are prime numbers.
     * Elements at odd indices are non-prime numbers.
     * In one operation, you may increment any element by 1.
     *
     * Return the minimum number of operations required to transform nums into an alternating prime array.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: nums = [1,2,3,4]
     * Output: 3
     *
     * Input: nums = [5,6,7,8]
     * Output: 0
     *
     * Input: nums = [4,4]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n + mloglogm), space = O(m)  m = 100010
    final int N = 100010;
    boolean[] st;
    public int minOperations(int[] nums) {
        st = new boolean[N + 10];
        init(N);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (i % 2 == 0) while (st[x]) x++;
            else while (!st[x]) x++;
            res += x - nums[i];
        }
        return res;
    }

    private void init(int n) {
        st[0] = st[1] = true;
        for (int i = 2; i <= n; i++) {
            if (st[i]) continue;
            for (int j = i + i; j <= n; j += i) st[j] = true;
        }
    }
}
