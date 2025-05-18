package LC2401_2700;
import java.util.*;
public class LC2570_MergeTwo2DArraysbySummingValues {
    /**
     * You are given two 2D integer arrays nums1 and nums2.
     *
     * nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
     * nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
     * Each array contains unique ids and is sorted in ascending order by id.
     *
     * Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
     *
     * Only ids that appear in at least one of the two arrays should be included in the resulting array.
     * Each id should be included only once and its value should be the sum of the values of this id in the two arrays.
     * If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
     * Return the resulting array. The returned array must be sorted in ascending order by id.
     *
     * Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
     * Output: [[1,6],[2,3],[3,2],[4,6]]
     *
     * Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
     * Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 200
     * nums1[i].length == nums2[j].length == 2
     * 1 <= idi, vali <= 1000
     * Both arrays contain unique ids.
     * Both arrays are in strictly ascending order by id.
     * @param nums1
     * @param nums2
     * @return
     */
    // S1
    // time = O(m + n), space = O(m + n)
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> res = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        int i, j;
        for (i = 0, j = 0; i < m && j < n;) {
            if (nums1[i][0] == nums2[j][0]) {
                res.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});
                i++;
                j++;
            } else if (nums1[i][0] < nums2[j][0]) {
                res.add(new int[]{nums1[i][0], nums1[i][1]});
                i++;
            } else {
                res.add(new int[]{nums2[j][0], nums2[j][1]});
                j++;
            }
        }
        while (i < m) res.add(nums1[i++]);
        while (j < n) res.add(nums2[j++]);
        return res.toArray(new int[res.size()][]);
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int[][] mergeArrays2(int[][] nums1, int[][] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] x : nums1) {
            int id = x[0], v = x[1];
            map.put(id, map.getOrDefault(id, 0) + v);
        }
        for (int[] x : nums2) {
            int id = x[0], v = x[1];
            map.put(id, map.getOrDefault(id, 0) + v);
        }
        int[][] res = new int[map.size()][2];
        int idx = 0;
        for (int k : map.keySet()) res[idx++] = new int[]{k, map.get(k)};
        Arrays.sort(res, (o1, o2) -> o1[0] - o2[0]);
        return res;
    }
}