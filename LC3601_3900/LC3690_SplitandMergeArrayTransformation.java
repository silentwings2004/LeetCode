package LC3601_3900;
import java.util.*;
public class LC3690_SplitandMergeArrayTransformation {
    /**
     * You are given two integer arrays nums1 and nums2, each of length n. You may perform the following split-and-merge
     * operation on nums1 any number of times:
     *
     * Choose a subarray nums1[L..R].
     * Remove that subarray, leaving the prefix nums1[0..L-1] (empty if L = 0) and the suffix nums1[R+1..n-1] (empty
     * if R = n - 1).
     * Re-insert the removed subarray (in its original order) at any position in the remaining array (i.e., between any
     * two elements, at the very start, or at the very end).
     * Return the minimum number of split-and-merge operations needed to transform nums1 into nums2.
     *
     * Input: nums1 = [3,1,2], nums2 = [1,2,3]
     *
     * Output: 1
     *
     * Input: nums1 = [1,1,2,3,4,5], nums2 = [5,4,3,2,1,1]
     *
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n == nums1.length == nums2.length <= 6
     * -10^5 <= nums1[i], nums2[i] <= 10^5
     * nums2 is a permutation of nums1.
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n! * n^4), space = O(n! * n^4)
    public int minSplitMerge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        String s = get(nums1), t = get(nums2);
        if (s.equals(t)) return 0;
        Queue<int[]> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        q.offer(nums1);
        set.add(s);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] x = q.poll();
                for (int l = 0; l < n; l++) {
                    for (int r = l; r < n; r++) {
                        int m = r - l + 1;
                        int[] y = Arrays.copyOfRange(x, l, r + 1);
                        int[] z = new int[n - m];
                        int idx = 0;
                        for (int i = 0; i < l; i++) z[idx++] = x[i];
                        for (int i = r + 1; i < n; i++) z[idx++] = x[i];
                        for (int i = 0; i <= n - m; i++) {
                            if (i == l) continue;
                            int[] w = new int[n];
                            idx = 0;
                            for (int j = 0; j < i; j++) w[idx++] = z[j];
                            for (int v : y) w[idx++] = v;
                            for (int j = i; j < n - m; j++) w[idx++] = z[j];
                            String h = get(w);
                            if (set.add(h)) {
                                if (h.equals(t)) return step + 1;
                                q.offer(w);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String get(int[] a) {
        return Arrays.toString(a);
    }
}
/**
 * 只要能够实现交换相邻2个元素 => 一定可以实现两个数组的转化
 * abcde -> abdce
 */