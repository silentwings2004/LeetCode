package LC3001_3300;
import java.util.*;
public class LC3002_MaximumSizeofaSetAfterRemovals {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of even length n.
     *
     * You must remove n / 2 elements from nums1 and n / 2 elements from nums2. After the removals, you insert the
     * remaining elements of nums1 and nums2 into a set s.
     *
     * Return the maximum possible size of the set s.
     *
     * Input: nums1 = [1,2,1,2], nums2 = [1,1,1,1]
     * Output: 2
     *
     * Input: nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
     * Output: 5
     *
     * Input: nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
     * Output: 6
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 2 * 10^4
     * n is even.
     * 1 <= nums1[i], nums2[i] <= 10^9
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m + n), space = O(m + n)
    HashMap<Integer, Integer> hm1, hm2;
    public int maximumSetSize(int[] nums1, int[] nums2) {
        int n = nums1.length;
        hm1 = new HashMap<>();
        hm2 = new HashMap<>();
        for (int x : nums1) hm1.put(x, hm1.getOrDefault(x, 0) + 1);
        for (int x : nums2) hm2.put(x, hm2.getOrDefault(x, 0) + 1);

        int t1 = n / 2, t2 = n / 2;
        for (int k : hm1.keySet()) {
            int v = hm1.get(k);
            if (v > 1) {
                int d = Math.min(v - 1, t1);
                t1 -= d;
                hm1.put(k, v - d);
                if (t1 == 0) break;
            }
        }
        for (int k : hm2.keySet()) {
            int v = hm2.get(k);
            if (v > 1) {
                int d = Math.min(v - 1, t2);
                t2 -= d;
                hm2.put(k, v - d);
                if (t2 == 0) break;
            }
        }
        return helper(t1, t2);
    }

    private int helper(int t1, int t2) {
        if (t1 > 0) {
            List<Integer> rem = new ArrayList<>();
            for (int k : hm1.keySet()) {
                if (hm2.getOrDefault(k, 0) > 0) {
                    t1--;
                    rem.add(k);
                    if (t1 == 0) break;
                }
            }
            for (int x : rem) hm1.remove(x);
        }
        if (t2 > 0) {
            List<Integer> rem = new ArrayList<>();
            for (int k : hm2.keySet()) {
                if (hm1.getOrDefault(k, 0) > 0) {
                    t2--;
                    rem.add(k);
                    if (t2 == 0) break;
                }
            }
            for (int x : rem) hm2.remove(x);
        }
        int dup = 0;
        for (int x : hm1.keySet()) {
            if (hm2.containsKey(x)) dup++;
        }
        if (dup > 0) return hm1.size() + hm2.size() - dup;
        return hm1.size() + hm2.size() - t1 - t2;
    }

    // S2
    // time = O(m + n), space = O(m + n)
    public int maximumSetSize2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int x : nums1) set1.add(x);
        for (int x : nums2) set2.add(x);
        int common = 0;
        for (int x : set1) {
            if (set2.contains(x)) common++;
        }

        int n1 = set1.size(), n2 = set2.size();
        int ans = n1 + n2 - common;

        int m = nums1.length / 2;
        if (n1 > m) {
            int mn = Math.min(n1 - m, common); // 只移除交集元素时，最多能移除 mn 个
            n1 -= mn;
            common -= mn;
            ans -= n1 - m;
        }
        if (n2 > m) {
            int mn = Math.min(n2 - m, common);
            n2 -= mn;
            ans -= n2 - m;
        }
        return ans;
    }
}
/**
 * 1.如果不移除 => 求并集大小
 * 2.优先移除重复的数字
 * 3.如果 nums1 的大小仍然超过 m = n / 2, 那么就继续移除元素
 *   优先移除交集中的元素
 */