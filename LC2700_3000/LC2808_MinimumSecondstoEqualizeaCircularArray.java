package LC2700_3000;
import java.util.*;
public class LC2808_MinimumSecondstoEqualizeaCircularArray {
    /**
     * You are given a 0-indexed array nums containing n integers.
     *
     * At each second, you perform the following operation on the array:
     *
     * For every index i in the range [0, n - 1], replace nums[i] with either nums[i], nums[(i - 1 + n) % n], or
     * nums[(i + 1) % n].
     * Note that all the elements get replaced simultaneously.
     *
     * Return the minimum number of seconds needed to make all elements in the array nums equal.
     *
     * Input: nums = [1,2,1,2]
     * Output: 1
     *
     * Input: nums = [2,1,3,3,2]
     * Output: 2
     *
     * Input: nums = [5,5,5,5]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
        }

        int res = n;
        for (int key : map.keySet()) {
            List<Integer> q = map.get(key);
            int m = q.size();
            if (m == 1) res = Math.min(res, n / 2);
            else {
                int t = 0;
                for (int i = 0; i < m; i++) {
                    int a = q.get(i), b = q.get((i + 1) % m);
                    if (i < m - 1) t = Math.max(t, b - a);
                    else t = Math.max(t, b + n - a);
                }
                res = Math.min(res, t / 2);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int minimumSeconds2(List<Integer> nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
        }

        int res = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            List<Integer> val = map.get(key);
            val.add(n + val.get(0));
            int d = 0;
            for (int i = 0; i + 1 < val.size(); i++) d = Math.max(d, (val.get(i + 1) - val.get(i)) / 2);
            res = Math.min(res, d);
        }
        return res;
    }
}