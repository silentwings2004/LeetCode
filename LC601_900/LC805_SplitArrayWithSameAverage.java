package LC601_900;
import java.util.*;
public class LC805_SplitArrayWithSameAverage {
    /**
     * You are given an integer array nums.
     *
     * You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and
     * average(A) == average(B).
     *
     * Return true if it is possible to achieve that and false otherwise.
     *
     * Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.
     *
     * Input: nums = [1,2,3,4,5,6,7,8]
     * Output: true
     *
     * Input: nums = [3,1]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 30
     * 0 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // S1: DFS + 折半查找
    // time = O(2^(n/2)), space = O(n)
    HashMap<Integer, Integer> map;
    int[] nums;
    public boolean splitArraySameAverage(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        if (n == 1) return false;
        int sum = 0;
        for (int x : nums) sum += x;
        for (int i = 0; i < n; i++) nums[i] = nums[i] * n - sum;

        int m = n / 2; // [0,m-1] [m, n-1]
        map = new HashMap<>();
        dfs1(m, n, 0);

        int s1 = 0, s2 = 0;
        // case 1: s1选空集，s2不能选空集
        if (map.getOrDefault(0, 0) > 1) return true;

        // case 2: s1选全集，则s2不能选全集
        for (int i = m; i < n; i++) s2 += nums[i];
        for (int i = 0; i < m; i++) s1 += nums[i];
        if (map.containsKey(-s1)) {
            if (-s1 != s2 || s2 == -s1 && map.get(s2) > 1) return true;
        }

        // case 3: 其他一般情况
        return dfs2(0, m, 0, 0);
    }

    private boolean dfs2(int u, int n, int sum, int cnt) {
        if (u == n) {
            if (cnt > 0 && cnt < n && map.containsKey(-sum)) return true;
            return false;
        } else {
            if (dfs2(u + 1, n, sum, cnt)) return true;
            if (dfs2(u + 1, n, sum + nums[u], cnt + 1)) return true;
            return false;
        }
    }

    private void dfs1(int u, int n, int sum) {
        if (u == n) map.put(sum, map.getOrDefault(sum, 0) + 1);
        else {
            dfs1(u + 1, n, sum);
            dfs1(u + 1, n, sum + nums[u]);
        }
    }

    // S2: state compression
    // time = O(2^(n / 2)), space = O(2^(n/2))
    public boolean splitArraySameAverage2(int[] nums) {
        int n = nums.length, tot = 0;
        for (int x : nums) tot += x;

        HashSet<String> set = new HashSet<>();
        int m = n / 2;
        for (int state = 0; state < (1 << m); state++) {
            int sum = 0, cnt = 0;
            for (int i = 0; i < m; i++) {
                if ((state >> i & 1) == 1) {
                    sum += nums[i];
                    cnt++;
                }
            }
            set.add(sum + "#" + cnt);
        }

        for (int state = 0; state < (1 << (n - m)); state++) {
            int sum = 0, cnt = 0;
            for (int i = 0; i < n - m; i++) {
                if ((state >> i & 1) == 1) {
                    sum += nums[m + i];
                    cnt++;
                }
            }

            for (int i = Math.max(1, cnt); i < n; i++) { // if cnt == 0, we can't choose the other half also as 0!
                if (i * tot % n == 0) {
                    int t = i * tot / n;
                    int x = t - sum, y = i - cnt; // enumerate the other half's possibilities inside the set
                    if (set.contains(x + "#" + y)) return true;
                }
            }
        }
        return false;
    }
}
/**
 * 折半搜索 -> 2^30变成2^15
 * avg = (x1 + x2 + ... + xn) / n
 * x1' = x1 - avg => 选出来的元素总和 = 0
 * 将每个元素扩展n倍，摒除精度问题
 * 1 <= cnt <= n - 1
 * 折半搜索：
 * 先平均分成2半，每一半 n / 2 个元素
 * 先去暴搜一下，左边n/2的个数可以凑出来的总和有哪些，然后扔到一个哈希表里
 * 然后看另一半能否凑出-sum
 * 边界问题：特判下就可以
 * 空集为一类，从右边非空里去选
 * 全集，右边全集都去掉再查
 * 非空非全集，直接去搜即可
 */