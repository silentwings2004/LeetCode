package LC1801_2100;
import java.util.*;
public class LC2032_TwoOutofThree {
    /**
     * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are
     * present in at least two out of the three arrays. You may return the values in any order.
     *
     * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
     * Output: [3,2]
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length, nums3.length <= 100
     * 1 <= nums1[i], nums2[j], nums3[k] <= 100
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    // S1: Set
    // time = O(n), space = O(n)
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> temp = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>(), res = new HashSet<>();
        for (int x : nums1) set.add(x);
        for (int x : nums2) {
            if (set.contains(x)) res.add(x);
            else temp.add(x);
        }
        set.addAll(temp);
        for (int x : nums3) {
            if (set.contains(x)) res.add(x);
        }
        return new ArrayList<>(res);
    }

    // S2: Bit
    // time = O(n), space = O(1)
    final int N = 110;
    public List<Integer> twoOutOfThree2(int[] nums1, int[] nums2, int[] nums3) {
        int[] cnt = new int[N];

        for (int x : nums1) cnt[x] |= 1;
        for (int x : nums2) cnt[x] |= 1 << 1;
        for (int x : nums3) cnt[x] |= 1 << 2;

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            if (Integer.bitCount(cnt[i]) >= 2) res.add(i);
        }
        return res;
    }
}