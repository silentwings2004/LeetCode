package LC3601_3900;

public class LC3739_CountSubarraysWithMajorityElementII {
    /**
     * You are given an integer array nums and an integer target.
     *
     * Return the number of subarrays of nums in which target is the majority element.
     *
     * The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,2,3], target = 2
     * Output: 5
     *
     * Input: nums = [1,1,1,1], target = 1
     * Output: 10
     *
     * Input: nums = [1,2,3], target = 4
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= target <= 10^9
     * @param nums
     * @param target
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, offset = n;
        Fenwick fen = new Fenwick(n * 2 + 1);
        fen.add(offset, 1); // 前缀和 = 0

        long res = 0;
        for (int i = 0, s = 0; i < n; i++) {
            s += nums[i] == target ? 1 : -1;
            int cur = s + offset;
            res += fen.sum(cur); // 查询所有小于当前 sum 前缀和的数量
            fen.add(cur, 1);
        }
        return res;
    }

    class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}