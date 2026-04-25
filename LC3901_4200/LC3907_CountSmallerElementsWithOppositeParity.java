package LC3901_4200;
import java.util.*;
public class LC3907_CountSmallerElementsWithOppositeParity {
    /**
     * You are given an integer array nums of length n.
     *
     * The score of an index i is defined as the number of indices j such that:
     *
     * i < j < n
     * nums[j] < nums[i]
     * nums[i] and nums[j] have different parity (one is even and the other is odd).
     * Return an integer array answer of length n, where answer[i] is the score of index i.
     *
     * Input: nums = [5,2,4,1,3]
     * Output: [2,1,2,0,0]
     *
     * Input: nums = [4,4,1]
     * Output: [1,1,0]
     *
     * Input: nums = [7]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] countSmallerOppositeParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] b = nums.clone();
        Arrays.sort(b);
        HashMap<Integer, Integer> map = new HashMap<>();
        int m = 0;
        for (int x : b) {
            if (!map.containsKey(x)) map.put(x, m++);
        }

        Fenwick fen0 = new Fenwick(m);
        Fenwick fen1 = new Fenwick(m);
        for (int i = n - 1; i >= 0; i--) {
            int v = nums[i], idx = map.get(v), p = v % 2;
            if (p == 0) {
                res[i] = fen1.sum(idx);
                fen0.add(idx, 1);
            } else {
                res[i] = fen0.sum(idx);
                fen1.add(idx, 1);
            }
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

        private int rangeSum(int l, int r) { // 左开右闭
            return sum(r) - sum(l);
        }
    }
}