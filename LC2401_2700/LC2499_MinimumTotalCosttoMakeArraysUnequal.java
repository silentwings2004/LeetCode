package LC2401_2700;
import java.util.*;
public class LC2499_MinimumTotalCosttoMakeArraysUnequal {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2, of equal length n.
     *
     * In one operation, you can swap the values of any two indices of nums1. The cost of this operation is the sum of
     * the indices.
     *
     * Find the minimum total cost of performing the given operation any number of times such that nums1[i] != nums2[i]
     * for all 0 <= i <= n - 1 after performing all the operations.
     *
     * Return the minimum total cost such that nums1 and nums2 satisfy the above condition. In case it is not possible,
     * return -1.
     *
     * Input: nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5]
     * Output: 10
     *
     * Input: nums1 = [2,2,2,1,3], nums2 = [1,2,2,3,3]
     * Output: 10
     *
     * Input: nums1 = [1,2,2], nums2 = [1,2,2]
     * Output: -1
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 10^5
     * 1 <= nums1[i], nums2[i] <= n
     * @param nums1
     * @param nums2
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length, maxf = 0, maxv = 0, swap = 0;
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] != nums2[i]) continue;
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
            if (map.get(nums1[i]) > maxf) {
                maxf = map.get(nums1[i]);
                maxv = nums1[i];
            }
            swap++;
            res += i;
        }

        for (int i = 0; i < n; i++) {
            if (maxf <= swap / 2) break;
            if (nums1[i] != nums2[i] && nums1[i] != maxv && nums2[i] != maxv) {
                res += i;
                swap++;
            }
        }
        if (maxf > swap / 2) return -1;
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public long minimumTotalCost2(int[] nums1, int[] nums2) {
        int n = nums1.length, tot = 0;
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                tot++;
                res += i;
                map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
            }
        }

        int maxFreq = 0, maxVal = 0;
        for (int key : map.keySet()) {
            if (map.get(key) > maxFreq) {
                maxFreq = map.get(key);
                maxVal = key;
            }
        }

        if (maxFreq <= tot / 2) return res;
        int extra = maxFreq - (tot - maxFreq);
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) continue;
            if (nums1[i] == maxVal || nums2[i] == maxVal) continue;
            extra--;
            res += i;
            if (extra == 0) break;
        }
        return extra == 0 ? res : -1;
    }
}
/**
 * res baseline
 * 大部分可以通过内部调整解决，我们只要把2和非2元素交换，就可以满足同一个pair里的两个元素不同。
 * 只要其中的majority element不超过same pairs的总数的一半即可。
 * 对于第二种情况，我们就需要借助于其他pair的帮助。
 * 我们会按下标从小到大去尝试。
 * a!=b，否则这是一个same pair，已经在“内部调整”时用过了。
 * a!=2且b!=2，否则交换之后仍然会有一个(2,2)。
 * 以此类推不断寻找下一个合适的distinct pair，直至将多余的(2,2)消耗掉。
 */
