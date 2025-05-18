package LC3001_3300;
import java.util.*;
public class LC3209_NumberofSubarraysWithANDValueofK {
    /**
     * Given an array of integers nums and an integer k, return the number of subarrays of nums where the bitwise AND of
     * the elements of the subarray equals k.
     *
     * Input: nums = [1,1,1], k = 1
     *
     * Output: 6
     *
     * Input: nums = [1,1,2], k = 1
     *
     * Output: 3
     *
     * Input: nums = [1,2,3], k = 2
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i], k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length, state = -1;
        int[] pos = new int[32];
        Arrays.fill(pos, -1);
        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            state &= nums[i];
            add(pos, nums[i], i);
            while (j <= i && state < k) {
                sub(pos, j);
                state = cal(pos, state);
                j++;
            }
            if (state == k) res += get(pos, k, j, i);
        }
        return res;
    }

    private void add(int[] pos, int x,int idx) {
        for (int i = 0; i < 32; i++) {
            if ((x >> i & 1) == 0) pos[i] = idx;
        }
    }

    private void sub(int[] pos, int idx) {
        for (int i = 0; i < 32; i++) {
            if (pos[i] == idx) pos[i] = -1;
        }
    }

    private int cal(int[] pos, int x) {
        for (int i = 0; i < 32; i++) {
            if ((x >> i & 1) == 0 && pos[i] == -1) x |= 1 << i;
        }
        return x;
    }

    private int get(int[] pos, int k, int l, int r) {
        for (int i = 0; i < 32; i++) {
            if ((k >> i & 1) == 1) {
                if (pos[i] != -1) l = Math.max(l, pos[i] + 1);
            } else r = Math.min(r, pos[i]);
        }
        return r - l + 1;
    }

    // S2
    // time = O(nlogU), space = O(n)
    public long countSubarrays2(int[] nums, int k) {
        HashMap<Integer, Long> f = new HashMap<>();
        long res = 0;
        for (int x : nums) {
            HashMap<Integer, Long> g = new HashMap<>();
            g.put(x, 1L);
            for (int y : f.keySet()) {
                g.put(x & y, g.getOrDefault(x & y, 0L) + f.get(y));
            }
            f = g;
            res += f.getOrDefault(k, 0L);
        }
        return res;
    }

    // S3: 3 pointers
    // time = O(n), space = O(1)
    public long countSubarrays3(int[] nums, int k) {
        long res = 0;
        int n = nums.length, l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = i - 1; j >= 0 && (x & nums[j]) != nums[j]; j--) {
                nums[j] &= x;
            }
            while (l <= i && nums[l] < k) l++;
            while (r <= i && nums[r] <= k) r++;
            res += r - l;
        }
        return res;
    }
}
/**
 * 对于[0,i]中的j, nums[j] 等于 nums[j] ~ nums[i] 的 AND
 * 从 nums[0] 到 nums[i], 相邻元素左边 <= 右边 非降数组
 * 可以直接二分 k 的第一个位置 left 和最后一个位置 right - 1
 * 由于 nums[j] 保存的是 nums[j] ~ nums[i] 的 AND
 * 那么随着 i 的变大，nums[j] 变小，有单调性
 * 三指针算法
 */