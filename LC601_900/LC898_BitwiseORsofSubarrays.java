package LC601_900;
import java.util.*;
public class LC898_BitwiseORsofSubarrays {
    /**
     * We have an array arr of non-negative integers.
     *
     * For every (contiguous) subarray sub = [arr[i], arr[i + 1], ..., arr[j]] (with i <= j), we take the bitwise OR of
     * all the elements in sub, obtaining a result arr[i] | arr[i + 1] | ... | arr[j].
     *
     * Return the number of possible results. Results that occur more than once are only counted once in the final
     * answer
     *
     * Input: arr = [0]
     * Output: 1
     *
     * Input: arr = [1,1,2]
     * Output: 3
     *
     * Input: arr = [1,2,4]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 0 <= nums[i] <= 10^9
     * @param arr
     * @return
     */
    // S1： brute-force
    // time = O(n^2), space = O(n)
    public int subarrayBitwiseORs(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                if ((arr[i] | arr[j]) == arr[j]) break;
                arr[j] |= arr[i];
                set.add(arr[j]);
            }
        }
        return set.size();
    }

    // S2: 递推
    // time = O(n), space = O(n)
    public int subarrayBitwiseORs2(int[] arr) {
        HashSet<Integer> res = new HashSet<>();
        HashSet<Integer> f = new HashSet<>();
        HashSet<Integer> g = new HashSet<>();

        for (int x : arr) {
            g.add(x);
            for (int y : f) g.add(y | x);
            for (int y : g) res.add(y);
            f = new HashSet<>(g);
            g.clear();
        }
        return res.size();
    }
}
/**
 * 不同数的个数最多只有30个 => 某位一旦变为1，就不可能再变回0了
 */