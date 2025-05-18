package LC2700_3000;
import java.util.*;
public class LC2948_MakeLexicographicallySmallestArraybySwappingElements {
    /**
     * You are given a 0-indexed array of positive integers nums and a positive integer limit.
     *
     * In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if
     * |nums[i] - nums[j]| <= limit.
     *
     * Return the lexicographically smallest array that can be obtained by performing the operation any number of times.
     *
     * An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a
     * has an element that is less than the corresponding element in b. For example, the array [2,10,3] is
     * lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.
     *
     * Input: nums = [1,5,3,9,8], limit = 2
     * Output: [1,3,5,8,9]
     *
     * Input: nums = [1,7,6,18,2,1], limit = 3
     * Output: [1,6,7,18,1,2]
     *
     * Input: nums = [1,7,28,19,10], limit = 3
     * Output: [1,7,28,19,10]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= limit <= 10^9
     * @param nums
     * @param limit
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n= nums.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{nums[i], i};
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && w[j][0] - w[j - 1][0] <= limit) {
                int a = w[j][1], b = w[i][1];
                p[find(a)] = find(b);
                j++;
            }
            i = j - 1;
        }

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int fa = find(i);
            map.putIfAbsent(fa, new ArrayList<>());
            map.get(fa).add(i);
        }

        int[] res = new int[n];
        for (List<Integer> x : map.values()) {
            List<Integer> y = new ArrayList<>();
            for (int idx : x) y.add(nums[idx]);
            Collections.sort(y);
            for (int i = 0, j = 0; i < y.size(); i++, j++) {
                res[x.get(j)] = y.get(i);
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int[] lexicographicallySmallestArray2(int[] nums, int limit) {
        int n = nums.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{nums[i], i};
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> q = new ArrayList<>();
            q.add(a[i][1]);
            int j = i + 1;
            while (j < n && a[j][0] - a[j - 1][0] <= limit) q.add(a[j++][1]);
            Collections.sort(q);
            for (int x : q) res[x] = a[i++][0];
            i--;
        }
        return res;
    }
}