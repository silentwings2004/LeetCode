package LC3001_3300;
import java.util.*;
public class LC3072_DistributeElementsIntoTwoArraysII {
    /**
     * You are given a 1-indexed array of integers nums of length n.
     *
     * We define a function greaterCount such that greaterCount(arr, val) returns the number of elements in arr that are
     * strictly greater than val.
     *
     * You need to distribute all the elements of nums between two arrays arr1 and arr2 using n operations. In the first
     * operation, append nums[1] to arr1. In the second operation, append nums[2] to arr2. Afterwards, in the ith
     * operation:
     *
     * If greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]), append nums[i] to arr1.
     * If greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]), append nums[i] to arr2.
     * If greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]), append nums[i] to the array with a lesser number
     * of elements.
     * If there is still a tie, append nums[i] to arr1.
     * The array result is formed by concatenating the arrays arr1 and arr2. For example, if arr1 == [1,2,3] and
     * arr2 == [4,5,6], then result = [1,2,3,4,5,6].
     *
     * Return the integer array result.
     *
     * Input: nums = [2,1,3,3]
     * Output: [2,3,1,3]
     *
     * Input: nums = [5,14,3,1,2]
     * Output: [5,3,1,2,14]
     *
     * Input: nums = [3,3,3,3]
     * Output: [3,3,3,3]
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] tr1, tr2;
    int n;
    public int[] resultArray(int[] nums) {
        List<Integer> q = new ArrayList<>();
        for (int x : nums) q.add(x);
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        n = q.size();

        tr1 = new int[n + 1];
        tr2 = new int[n + 1];
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        int x = find(q, nums[0]), y = find(q, nums[1]);
        add(tr1, x + 1, 1);
        arr1.add(nums[0]);
        add(tr2, y + 1, 1);
        arr2.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int pos = find(q, nums[i]);
            int cnt1 = arr1.size() - sum(tr1, pos + 1), cnt2 = arr2.size() - sum(tr2, pos + 1);
            if (cnt1 > cnt2) {
                arr1.add(nums[i]);
                add(tr1, pos + 1, 1);
            } else if (cnt1 < cnt2) {
                arr2.add(nums[i]);
                add(tr2, pos + 1, 1);
            } else {
                if (arr1.size() <= arr2.size()) {
                    arr1.add(nums[i]);
                    add(tr1, pos + 1, 1);
                } else {
                    arr2.add(nums[i]);
                    add(tr2, pos + 1, 1);
                }
            }
        }
        int[] res = new int[nums.length];
        for (int i = 0, j = 0; j < nums.length; i++, j++) {
            if (i < arr1.size()) res[j] = arr1.get(i);
            else res[j] = arr2.get(i - arr1.size());
        }
        return res;
    }

    private int find(List<Integer> q, int x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int[] tr, int x, int c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += c;
    }

    private int sum(int[] tr, int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}