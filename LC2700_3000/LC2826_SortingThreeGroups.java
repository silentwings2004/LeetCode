package LC2700_3000;
import java.util.*;
public class LC2826_SortingThreeGroups {
    /**
     * You are given a 0-indexed integer array nums of length n.
     *
     * The numbers from 0 to n - 1 are divided into three groups numbered from 1 to 3, where number i belongs to group
     * nums[i]. Notice that some groups may be empty.
     *
     * You are allowed to perform this operation any number of times:
     *
     * Pick number x and change its group. More formally, change nums[x] to any number from 1 to 3.
     * A new array res is constructed using the following procedure:
     *
     * Sort the numbers in each group independently.
     * Append the elements of groups 1, 2, and 3 to res in this order.
     * Array nums is called a beautiful array if the constructed array res is sorted in non-decreasing order.
     *
     * Return the minimum number of operations to make nums a beautiful array.
     *
     * Input: nums = [2,1,3,2,1]
     * Output: 3
     *
     * Input: nums = [1,3,2,1,3,3]
     * Output: 2
     *
     * Input: nums = [2,2,2,2,3,3]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 3
     * @param nums
     * @return
     */
    // S1: dp
    // time = O(n), space = O(n)
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size(), INF = (int)1e9;
        int[][] f = new int[n + 1][3];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], INF);
        f[0][0] = f[0][1] = f[0][2] = 0;

        for (int i = 1; i <= n; i++) {
            f[i][0] = f[i - 1][0] + (nums.get(i - 1) == 1 ? 0 : 1);
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + (nums.get(i - 1) == 2 ? 0 : 1);
            f[i][2] = Math.min(f[i - 1][0], Math.min(f[i - 1][1], f[i - 1][2])) + (nums.get(i - 1) == 3 ? 0 : 1);
        }
        return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
    }

    // S2
    // time = O(n^3), space = O(1)
    public int minimumOperations2(List<Integer> nums) {
        int n = nums.size(), res = n;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j + i <= n; j++) {
                int cnt = 0;
                for (int k = 0; k < n; k++) {
                    int t = 0;
                    if (k <= i - 1) t = 1;
                    else if (k <= i + j - 1) t = 2;
                    else t = 3;
                    if (t != nums.get(k)) cnt++;
                }
                res = Math.min(res, cnt);
            }
        }
        return res;
    }

    // S3: LIS
    // time = O(nlogn), space = O(n)
    public int minimumOperations3(List<Integer> nums) {
        List<Integer> q = new ArrayList<>();
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int pos = find(q, x);
            if (pos == q.size()) q.add(x);
            else q.set(pos, x);
        }
        return n - q.size();
    }

    private int find(List<Integer> q, int x) {
        if (q.size() == 0) return 0;
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) > x) r = mid;
            else l = mid + 1;
        }
        return q.get(r) > x ? r : r + 1;
    }
}