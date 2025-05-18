package LC901_1200;
import java.util.*;
public class LC989_AddtoArrayFormofInteger {
    /**
     * The array-form of an integer num is an array representing its digits in left to right order.
     *
     * For example, for num = 1321, the array form is [1,3,2,1].
     * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
     *
     * Input: num = [1,2,0,0], k = 34
     * Output: [1,2,3,4]
     *
     * Input: num = [2,7,4], k = 181
     * Output: [4,5,5]
     *
     * Input: num = [2,1,5], k = 806
     * Output: [1,0,2,1]
     *
     * Constraints:
     *
     * 1 <= num.length <= 10^4
     * 0 <= num[i] <= 9
     * num does not contain any leading zeros except for the zero itself.
     * 1 <= k <= 10^4
     * @param num
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int n = num.length;
        for (int i = n - 1, t = k; i >= 0 || t > 0; i--) {
            if (i >= 0) t += num[i];
            res.add(t % 10);
            t /= 10;
        }
        Collections.reverse(res);
        return res;
    }
}