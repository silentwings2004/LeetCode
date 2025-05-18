package LC1501_1800;
import java.util.*;
public class LC1630_ArithmeticSubarrays {
    /**
     * A sequence of numbers is called arithmetic if it consists of at least two elements, and the difference between
     * every two consecutive elements is the same. More formally, a sequence s is arithmetic if and only if
     * s[i+1] - s[i] == s[1] - s[0] for all valid i.
     *
     * For example, these are arithmetic sequences:
     *
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * The following sequence is not arithmetic:
     *
     * 1, 1, 2, 5, 7
     * You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m range
     * queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
     *
     * Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... ,
     * nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.
     *
     * Input: nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
     * Output: [true,false,true]
     *
     * Input: nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
     * Output: [false,true,false,false,true,true]
     *
     * Constraints:
     *
     * n == nums.length
     * m == l.length
     * m == r.length
     * 2 <= n <= 500
     * 1 <= m <= 500
     * 0 <= l[i] < r[i] < n
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @param l
     * @param r
     * @return
     */
    // time = O(m * nlogn), space = O(n)
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = l[i], b = r[i];
            if (b - a == 1) {
                res.add(true);
                continue;
            }
            int[] w = new int[b - a + 1];
            for (int j = a; j <= b; j++) w[j - a] = nums[j];
            Arrays.sort(w);
            int diff = w[1] - w[0];
            boolean flag = true;
            for (int j = 0; j + 1 < w.length; j++) {
                if (w[j + 1] - w[j] != diff) {
                    flag = false;
                    break;
                }
            }
            res.add(flag);
        }
        return res;
    }
}