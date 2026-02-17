package LC3601_3900;

public class LC3845_MaximumSubarrayXORwithBoundedRange {
    /**
     * You are given a non-negative integer array nums and an integer k.
     *
     * You must select a subarray of nums such that the difference between its maximum and minimum elements is at most
     * k. The value of this subarray is the bitwise XOR of all elements in the subarray.
     *
     * Return an integer denoting the maximum possible value of the selected subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,4,5,6], k = 2
     * Output: 7
     *
     * Input: nums = [5,4,5,6], k = 1
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 4 * 10^4
     * 0 <= nums[i] < 2^15
     * 0 <= k < 2^15
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * L), space = O(n * L), L: 15
    TrieNode root;
    public int maxXor(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] ^ nums[i - 1];
        int res = 0;
        root = new TrieNode();

        int[] dq1 = new int[n + 1]; // max
        int[] dq2 = new int[n + 1]; // min
        int hh1 = 0, tt1 = -1, hh2 = 0, tt2 = -1;
        for (int i = 0, j = 0; i < n; i++) {
            insert(s[i]);
            while (hh1 <= tt1 && nums[dq1[tt1]] <= nums[i]) tt1--;
            while (hh2 <= tt2 && nums[dq2[tt2]] >= nums[i]) tt2--;
            dq1[++tt1] = i;
            dq2[++tt2] = i;
            while (nums[dq1[hh1]] - nums[dq2[hh2]] > k) {
                remove(s[j++]);
                if (dq1[hh1] < j) hh1++;
                if (dq2[hh2] < j) hh2++;
            }
            res = Math.max(res, query(s[i + 1]));
        }
        return res;
    }

    private void insert(int x) {
        TrieNode p = root;
        for (int i = 14; i >= 0; i--) {
            int u = x >> i & 1;
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
            p.cnt++;
        }
    }

    private void remove(int x) {
        TrieNode p = root;
        for (int i = 14; i >= 0; i--) {
            int u = x >> i & 1;
            p = p.next[u];
            p.cnt--;
        }
    }

    private int query(int x) {
        TrieNode p = root;
        int res = 0;
        for (int i = 14; i >= 0; i--) {
            int u = x >> i & 1;
            if (p.next[u ^ 1] != null && p.next[u ^ 1].cnt > 0) {
                res |= 1 << i;
                p = p.next[u ^ 1];
            } else if (p.next[u] != null && p.next[u].cnt > 0) {
                p = p.next[u];
            } else return 0;
        }
        return res;
    }

    class TrieNode {
        TrieNode[] next;
        int cnt;
        public TrieNode() {
            this.next = new TrieNode[2];
            this.cnt = 0;
        }
    }

    // S1.2
    // time = O(n * L), space = O(n * L), L: 15
    class Solution {
        final int N = 40010 * 2;
        int[][] son;
        int[] cnt;
        int idx;
        public int maxXor(int[] nums, int k) {
            son = new int[N][2];
            cnt = new int[N];
            int n = nums.length;
            int[] s = new int[n + 1];
            for (int i = 1; i <= n; i++) s[i] = s[i - 1] ^ nums[i - 1];

            int res = 0;
            int[] dq1 = new int[n + 1]; // max
            int[] dq2 = new int[n + 1]; // min
            int hh1 = 0, tt1 = -1, hh2 = 0, tt2 = -1;
            for (int i = 0, j = 0; i < n; i++) {
                while (hh1 <= tt1 && nums[dq1[tt1]] <= nums[i]) tt1--;
                while (hh2 <= tt2 && nums[dq2[tt2]] >= nums[i]) tt2--;
                dq1[++tt1] = i;
                dq2[++tt2] = i;

                while (nums[dq1[hh1]] - nums[dq2[hh2]] > k) {
                    remove(s[j++]);
                    if (dq1[hh1] < j) hh1++;
                    if (dq2[hh2] < j) hh2++;
                }
                insert(s[i]);
                res = Math.max(res, query(s[i + 1]));
            }
            return res;
        }

        private void insert(int x) {
            int p = 0;
            for (int i = 14; i >= 0; i--) {
                int u = x >> i & 1;
                if (son[p][u] == 0) son[p][u] = ++idx;
                p = son[p][u];
                cnt[p]++;
            }
        }

        private void remove(int x) {
            int p = 0;
            for (int i = 14; i >= 0; i--) {
                int u = x >> i & 1;
                p = son[p][u];
                cnt[p]--;
            }
        }

        private int query(int x) {
            int p = 0, res = 0;
            for (int i = 14; i >= 0; i--) {
                int u = x >> i & 1;
                if (son[p][u ^ 1] != 0 && cnt[son[p][u ^ 1]] > 0) {
                    res |= 1 << i;
                    p = son[p][u ^ 1];
                } else if (son[p][u] != 0 && cnt[son[p][u]] > 0) p = son[p][u];
                else return 0;
            }
            return res;
        }
    }
}