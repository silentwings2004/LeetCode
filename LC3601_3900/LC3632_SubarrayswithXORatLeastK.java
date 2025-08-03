package LC3601_3900;

public class LC3632_SubarrayswithXORatLeastK {
    /**
     * Given an array of positive integers nums of length n and a non‑negative integer k.
     *
     * Return the number of contiguous subarrays whose bitwise XOR of all elements is greater than or equal to k.
     *
     * Input: nums = [3,1,2,3], k = 2
     * Output: 6
     *
     * Input: nums = [0,0,0], k = 0
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogm), space = O(nlogm)
    TrieNode root;
    int add;
    public long countXorSubarrays(int[] nums, int k) {
        root = new TrieNode();
        long res = 0;
        int v = 0;
        insert(0);
        add = 1;

        for (int x : nums) {
            v ^= x;
            int y = cal(v, k); // how many of inserted prefixes produce xor < k
            res += add - y; // the rest produce xor >= k
            insert(v);
            add++;
        }
        return res;
    }

    private void insert(int x) {
        TrieNode p = root;
        p.cnt++;
        for (int i = 30; i >= 0; i--) {
            int u = x >> i & 1;
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
            p.cnt++;
        }
    }

    private int cal(int x, int k) { // Count how many y in trie satisfy (y ^ x) < k
        TrieNode p = root;
        int res = 0;
        for (int i = 30; i >= 0 && p != null; i--) {
            int u = x >> i & 1, v = k >> i & 1;
            if (v == 1) {
                if (p.next[u] != null) res += p.next[u].cnt; // for y-bit = u, (x^y) >> b = 0 < 1: take all those
                p = p.next[u ^ 1]; // must go to branch where y-bit = u ^ 1 to match x^y bit = 1
            } else p = p.next[u]; // must stay in branch where x^y bit = 0 → y-bit = u
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
}