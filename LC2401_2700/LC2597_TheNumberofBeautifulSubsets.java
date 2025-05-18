package LC2401_2700;
import java.util.*;
public class LC2597_TheNumberofBeautifulSubsets {
    /**
     * You are given an array nums of positive integers and a positive integer k.
     *
     * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
     *
     * Return the number of non-empty beautiful subsets of the array nums.
     *
     * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two
     * subsets are different if and only if the chosen indices to delete are different.
     *
     * Input: nums = [2,4,6], k = 2
     * Output: 4
     *
     * Input: nums = [1], k = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 20
     * 1 <= nums[i], k <= 1000
     * @param nums
     * @param k
     * @return
     */
    // S1: state compression
    // time = O(n * 2^n), space = O(2^n)
    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        int[] id = new int[1 << n];
        for (int i = 0; i < n; i++) id[1 << i] = i;
        int[] st = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (Math.abs(nums[i] - nums[j]) == k) {
                    st[i] |= 1 << j;
                }
            }
        }

        int res = 0;
        for (int state = 1; state < 1 << n; state++) {
            int cur = 0;
            boolean flag = true;
            for (int i = state; i > 0; i -= lowbit(i)) {
                int t = id[lowbit(i)];
                if ((cur & st[t]) != 0) {
                    flag = false;
                    break;
                } else cur |= 1 << t;
            }
            if (flag) res++;
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    // S2: dfs
    // time = O(2^n), space = O(n)
    public int beautifulSubsets2(int[] nums, int k) {
        return dfs(0, 0, nums, k) - 1; // 去掉空集
    }

    private int dfs(int cur, int state, int[] nums, int k) {
        if (cur == nums.length) return 1;
        boolean flag = true;
        for (int i = 0; i < cur; i++) {
            if ((state >> i & 1) == 1 && Math.abs(nums[i] - nums[cur]) == k) {
                flag = false;
                break;
            }
        }

        int take = dfs(cur + 1, state + (1 << cur), nums, k);
        int notake = dfs(cur + 1, state, nums, k);
        if (!flag) return notake;
        else return take + notake;
    }

    // S3: DP
    // time = O(n), space = O(n)
    public int beautifulSubsets3(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int x : nums) count.put(x, count.getOrDefault(x, 0) + 1);
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int key : count.keySet()) {
            int v = key % k;
            map.putIfAbsent(v, new ArrayList<>());
            map.get(v).add(new int[]{key, count.get(key)});
        }

        int res = 1;
        for (int key : map.keySet()) {
            List<int[]> q = map.get(key);
            Collections.sort(q, (o1, o2) -> o1[0] - o2[0]);
            int take = 0, notake = 1;
            for (int i = 0; i < q.size(); i++) {
                int take_tmp = take, notake_tmp = notake;
                if (i > 0 && Math.abs(q.get(i)[0] - q.get(i - 1)[0]) == k) {
                    take = notake_tmp * ((1 << q.get(i)[1]) - 1);
                    notake = take_tmp + notake_tmp;
                } else {
                    take = (take_tmp + notake_tmp) * ((1 << q.get(i)[1]) - 1);
                    notake = take_tmp + notake_tmp;
                }
            }
            res = res * (take + notake);
        }
        return res - 1;
    }

    // S4: 枚举
    // time = O(n * 2^n), space = O(n)
    public int beautifulSubsets4(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 1; i < 1 << n; i++) {
            HashSet<Integer> set = new HashSet<>();
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    int v = nums[j];
                    int x = v - k, y = v + k;
                    if (set.contains(x) || set.contains(y)) {
                        flag = false;
                        break;
                    }
                    set.add(v);
                }
            }
            if (flag) res++;
        }
        return res;
    }

    // S5: dfs + backtracking
    // time = O(2^n), space = O(n)
    int[] nums, cnt;
    int k, res;
    public int beautifulSubsets5(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        cnt = new int[3010];
        f(0);
        return res - 1;
    }

    private void f(int u) {
        if (u == nums.length) res++;
        else {
            f(u + 1);
            int x = nums[u] - k, y = nums[u] + k;
            if (cnt[x + 1000] == 0 && cnt[y + 1000] == 0) {
                cnt[nums[u] + 1000]++;
                f(u + 1);
                cnt[nums[u] + 1000]--;
            }
        }
    }
}
/**
 * num % k = 0,1,2,...k-1
 * 分成k类
 * 我们将所有的数字按照对k取模分类。
 * 对于位于不同类的数字，彼此之间是否选取都是没有制约关系的。
 * 假设第一类数字里我们有k1种选法，第一类数字里我们有k2种选法，...，那么最终的答案就是k1*k2*....
 * 对于同一类的数字，我们将其去重并从小到大排序之后，相邻元素之间的差值必然是k的整数倍。
 * 此时的问题转化为：总共有多少种元素的取法，要求相邻元素如果恰好相差k的话不能同时取。
 * 这就是典型的house robber问题。
 * 我们维护两个变量：take表示假设当前元素被选取的话，有多少种组合方法；notake表示当前元素不被选取的话，有多少种方法。
 * take[i]: the number of combs if we take the ith element
 * notake[i]: the number of combs if we do not take the ith element
 * if (abs(nums[i] - nums[i - 1]) == k) {
 *     take[i] = notake[i - 1] * (2^t-1);
 *     notake[i] = (take[i - 1] + notake[i - 1]) * 1;
 * }  else {
 *     take[i] = (take[i - 1] + notake[i - 1]) * (2^t-1);
 *     notake[i] = (take[i - 1] + notake[i - 1]) * 1;
 * }
 * return take[m - 1] + notake[m - 1]
 */