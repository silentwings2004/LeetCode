package LC2700_3000;
import java.util.*;
public class LC2935_MaximumStrongPairXORII {
    /**
     * You are given a 0-indexed integer array nums. A pair of integers x and y is called a strong pair if it satisfies
     * the condition:
     *
     * |x - y| <= min(x, y)
     * You need to select two integers from nums such that they form a strong pair and their bitwise XOR is the maximum
     * among all strong pairs in the array.
     *
     * Return the maximum XOR value out of all possible strong pairs in the array nums.
     *
     * Note that you can pick the same integer twice to form a pair.
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 7
     *
     * Input: nums = [10,100]
     * Output: 0
     *
     * Input: nums = [500,520,2500,3000]
     * Output: 1020
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 2^20 - 1
     * @param nums
     * @return
     */
    // S1: XOR Trie
    // time = O(nlogn + nlogk), space = O(nlogk)  k: max(nums)
    int[][] son;
    int[] cnt;
    int idx;
    public int maximumStrongPairXor(int[] nums) {
        int n = nums.length, m = (n + 10) * 20;
        son = new int[m][2];
        cnt = new int[m];

        Arrays.sort(nums);
        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < i && nums[i] - nums[j] > nums[j]) remove(nums[j++]);
            insert(nums[i]);
            res = Math.max(res, query(nums[i]));
        }
        return res;
    }

    private void insert(int x) {
        int p = 0;
        for (int i = 20; i >= 0; i--) {
            int u = x >> i & 1;
            if (son[p][u] == 0) son[p][u] = ++idx;
            p = son[p][u];
            cnt[p]++;
        }
    }

    private void remove(int x) {
        int p = 0;
        for (int i = 20; i >= 0; i--) {
            int u = x >> i & 1;
            p = son[p][u];
            cnt[p]--;
        }
    }

    private int query(int x) {
        int p = 0, sum = 0;
        for (int i = 20; i >= 0; i--) {
            int u = x >> i & 1;
            if (son[p][u ^ 1] != 0 && cnt[son[p][u ^ 1]] > 0) {
                p = son[p][u ^ 1];
                sum = sum * 2 + 1;
            } else {
                p = son[p][u];
                sum *= 2;
            }
        }
        return sum;
    }

    // S2: HashMap
    // time = O(nlogn + nlogk), space = O(n)  k: max(nums)
    public int maximumStrongPairXor2(int[] nums) {
        Arrays.sort(nums);
        int res = 0, mask = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 31; i >= 0; i--) {
            map.clear();
            mask |= 1 << i;
            int ns = res | (1 << i);
            for (int y : nums) {
                int mask_y = y & mask;
                if (map.containsKey(ns ^ mask_y) && map.get(ns ^ mask_y) * 2 >= y) {
                    res = ns;
                    break;
                }
                map.put(mask_y, y);
            }
        }
        return res;
    }
}