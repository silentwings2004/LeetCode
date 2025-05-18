package LC1501_1800;
import java.util.*;
public class LC1521_FindaValueofaMysteriousFunctionClosesttoTarget {
    /**
     * Winston was given the above mysterious function func. He has an integer array arr and an integer target and he
     * wants to find the values l and r that make the value |func(arr, l, r) - target| minimum possible.
     *
     * Return the minimum possible value of |func(arr, l, r) - target|.
     *
     * Notice that func should be called with the values l and r where 0 <= l, r < arr.length.
     *
     * Input: arr = [9,12,3,7,15], target = 5
     * Output: 2
     *
     * Input: arr = [1000000,1000000,1000000], target = 1
     * Output: 999999
     *
     * Input: arr = [1,2,4,8,16], target = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * 1 <= arr[i] <= 10^6
     * 0 <= target <= 10^7
     * @param arr
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int closestToTarget(int[] arr, int target) {
        HashSet<Integer> s1 = new HashSet<>();
        int n = arr.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> s2 = new HashSet<>();
            for (int x : s1) s2.add(x & arr[i]);
            s2.add(arr[i]);

            for (int x : s2) res = Math.min(res, Math.abs(x - target));
            s1 = new HashSet<>(s2);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    int[][] s;
    public int closestToTarget2(int[] arr, int target) {
        int n = arr.length;
        s = new int[20][n + 1];
        for (int i = 0; i < 20; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i][j - 1];
                if ((arr[j - 1] >> i & 1) == 0) s[i][j]++;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1, j = 1; i <= n; i++) {
            while (j + 1 <= i && get_sum(j + 1, i) <= target) j++;
            res = Math.min(res, Math.abs(get_sum(j, i) - target));
            if (j < i) res = Math.min(res, Math.abs(get_sum(j + 1, i) - target));
        }
        return res;
    }

    private int get_sum(int l, int r) {
        int res = 0;
        for (int i = 0; i < 20; i++) {
            if (s[i][r] - s[i][l - 1] == 0) res += 1 << i;
        }
        return res;
    }
}
/**
 * 1.
 * subarray[i:j] sum = presum[j] - presum[i]
 * subarray[i:j] xor = prexor[j] - prexor[i]
 * 2. dp
 * dp[i]: the subarray ending at i which bitwise AND is closest to target
 * x x x x [x x x x i]
 *                ^
 *                j
 * dp[i] => dp[i-1]
 * dp[i] => dp[j] for j = 0,1,...i-1
 * dp[i] = best {A[i], A[i-1:i], A[i-2:i], A[1:i]} => max number 32
 * dp[i-1] = best {A[i-1], A[i-2:i-1], A[i-3:i],...A[i:i-1]} => max number 32
 *
 * 考虑s集合包含了所有以元素i结尾的subarray的bitwise AND的结果。
 * 那么这个集合有多大呢？其实并没有i个，而是最多只有32个。
 * 因为这些subarray的bitwise AND的本质，都是针对A[i]的某些bit进行从1到0的翻转。
 * 并且这些subarray bitwise AND从后往前来看都是不可逆的。
 * 具体地说，A[i], A[i]&A[i-1], A[i]&A[i-1]&A[i-2], ... 它们的bit 1的个数只会越来越少。
 * 因为A[i]最多只有32个1，所以所有这些subarray最多只会有32个不同的bitwise AND的结果。
 * 既然s只有32个元素，那么只需要遍历一遍就可以找到最接近target的值。
 * 然后在考虑A[i+1]，并更新s集合：只需要将每个s的元素与A[i+1]进行AND，再添加A[i+1]即可。
 */