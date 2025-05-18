package LC2101_2400;
import java.util.*;
public class LC2305_FairDistributionofCookies {
    /**
     * You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are
     * also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the
     * cookies in the same bag must go to the same child and cannot be split up.
     *
     * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the
     * distribution.
     *
     * Return the minimum unfairness of all distributions.
     *
     * Input: cookies = [8,15,10,20,8], k = 2
     * Output: 31
     *
     * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
     * Output: 7
     *
     *
     Constraints:

     2 <= cookies.length <= 8
     1 <= cookies[i] <= 10^5
     2 <= k <= cookies.length
     * @param cookies
     * @param k
     * @return
     */
    // S1: DFS
    // time = O(n^n), space = O(n)
    int[] sum, q;
    int k, ans;
    public int distributeCookies(int[] cookies, int k) {
        sum = new int[k];
        q = cookies;
        this.k = k;
        ans = Integer.MAX_VALUE;

        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (u == q.length) {
            int max = 0;
            for (int i = 0; i < k; i++) max = Math.max(max, sum[i]);
            ans = Math.min(ans, max);
            return;
        }

        for (int i = 0; i < k; i++) {
            sum[i] += q[u];
            if (sum[i] < ans) dfs(u + 1);
            sum[i] -= q[u];
            if (sum[i] == 0) break; // 所有孩子的0都是identical的，backtracking后如果这个孩子为0，那么下面孩子的情况是一样的。
        }
    }

    // S2: bs + dfs
    int[] plan; // plan[i]: the # of cookies that the i-th person get
    public int distributeCookies2(int[] cookies, int k) {
        Arrays.sort(cookies);
        reverse(cookies);
        plan = new int[8];
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            Arrays.fill(plan, 0);
            int mid = l + r >> 1;
            if (helper(cookies, k, mid, 0)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean helper(int[] cookies, int k, int limit, int curCookie) {
        if (curCookie == cookies.length) return true;

        boolean flag = false;
        for (int i = 0; i < k; i++) {
            if (plan[i] + cookies[curCookie] > limit) continue;
            if (plan[i] == 0) { // 遇到其他一无所有的人都直接跳过！！！
                if (flag) continue;
                flag = true;
            }
            plan[i] += cookies[curCookie];
            if (helper(cookies, k, limit, curCookie + 1)) return true;
            plan[i] -= cookies[curCookie];
        }
        return false;
    }

    private void reverse(int[] a) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    // S3: bitmask
    // O(
    public int distributeCookies3(int[] cookies, int k) {
        int n = cookies.length;
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + r >> 1;
            if (helper2(cookies, k, mid, 0, (1 << n) - 1)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean helper2(int[] cookies, int k, int limit, int curPerson, int curLeft) {
        if (curPerson == k) return curLeft == 0; // 饼干也要分配完,不能直接return true!!!

        for (int subset = curLeft; subset > 0; subset = (subset - 1) & curLeft) {
            int sum = getSum(cookies, subset);
            if (sum > limit) continue;
            if (helper2(cookies, k, limit, curPerson + 1, curLeft - subset)) return true;
        }
        return false;
    }

    private int getSum(int[] cookies, int state) {
        int n = cookies.length, res = 0;
        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1) res += cookies[i];
        }
        return res;
    }
}
/**
 * 暴力搜索，数据规模只有8
 * time = O(8^8) = O(2^24) = O(10^6 * 2^4) = 1.6 * 10^7
 * 模拟退火 -> if n = 20 超时
 * 用启发式搜索 AC2680
 * 模拟每个数据放到哪个组
 *
 * Ref: same as LC1723
 * 经典NP问题 -> 暴力
 * 正面硬杠不行 => 二分搜值
 * 找一种分配方案，使得每个人的分配数都不超过x
 * dfs (cookie) k branches
 * bs + dfs (cookies) -> 给定上限，给dfs提供剪枝依据
 * bs + dfs (person)
 */