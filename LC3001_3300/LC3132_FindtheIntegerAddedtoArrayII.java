package LC3001_3300;
import java.util.*;
public class LC3132_FindtheIntegerAddedtoArrayII {
    /**
     * You are given two integer arrays nums1 and nums2.
     *
     * From nums1 two elements have been removed, and all other elements have been increased (or decreased in the case
     * of negative) by an integer, represented by the variable x.
     *
     * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers
     * with the same frequencies.
     *
     * Return the minimum possible integer x that achieves this equivalence.
     *
     * Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
     * Output: -2
     *
     * Input: nums1 = [3,5,5,3], nums2 = [7,7]
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= nums1.length <= 200
     * nums2.length == nums1.length - 2
     * 0 <= nums1[i], nums2[i] <= 1000
     * The test cases are generated in a way that there is an integer x such that nums1 can become equal to nums2 by
     * removing two elements and adding x to each element of nums1.
     * @param nums1
     * @param nums2
     * @return
     */
    // S1
    // time = O(n^3), space = O(logn)
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums2.length, inf = 0x3f3f3f3f, res = inf;
        for (int i = 0; i < n + 2; i++) {
            for (int j = i + 1; j < n + 2; j++) {
                int t = inf;
                boolean flag = true;
                for (int a = 0, b = 0; a < n + 2 && b < n;) {
                    if (a == i || a == j) {
                        a++;
                        continue;
                    }
                    int d = nums2[b] - nums1[a];
                    if (t == inf) {
                        t = d;
                        a++;
                        b++;
                    } else {
                        if (t != d) {
                            flag = false;
                            break;
                        } else {
                            a++;
                            b++;
                        }
                    }
                }
                if (flag) {
                    res = t;
                    break;
                }
            }
            if (res != inf) break;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public int minimumAddedInteger2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int m = nums1.length, n = nums2.length;
        int res = 0;
        for (int u = 2; u >= 0; u--) {
            int d = nums2[0] - nums1[u];
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (nums2[j] - nums1[i] == d) j++;
                i++;
            }
            if (j == n) {
                res = d;
                break;
            }
        }
        return res;
    }
}
/**
 * x 其实只有3种情况
 * b0 - a0
 * b0 - a1
 * b0 - a2
 */