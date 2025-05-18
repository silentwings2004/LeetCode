package LC2401_2700;
import java.util.*;
public class LC2659_MakeArrayEmpty {
    /**
     * You are given an integer array nums containing distinct numbers, and you can perform the following operations
     * until the array is empty:
     *
     * If the first element has the smallest value, remove it
     * Otherwise, put the first element at the end of the array.
     * Return an integer denoting the number of operations it takes to make nums empty.
     *
     * Input: nums = [3,4,-1]
     * Output: 5
     *
     * Input: nums = [1,2,4,3]
     * Output: 5
     *
     * Input: nums = [1,2,3]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -109 <= nums[i] <= 10^9
     * All values in nums are distinct.
     * @param nums
     * @return
     */
    // S1: Sort
    // time = O(nlogn), space = O(n)
    public long countOperationsToEmptyArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) map.put(nums[i], i);
        Arrays.sort(nums);

        long res = n;
        for (int i = 1; i < n; i++) {
            if (map.get(nums[i]) < map.get(nums[i - 1])) res += n - i;
        }
        return res;
    }

    // S2: BIT
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    int[] tr;
    public long countOperationsToEmptyArray2(int[] nums) {
        tr = new int[N];
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nums[i], i);
        Arrays.sort(nums);

        long res = n;
        int last = -1;
        for (int i = 0; i < n; i++) {
            int x = map.get(nums[i]);
            if (last == -1) res += x;
            else {
                if (x > last) res += x - last - (query(x) - query(last));
                else res += (n + x - last) - query(x) - (query(n) - query(last));
            }
            add(x + 1, 1);
            last = x;
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i < N; i += lowbit(i)) tr[i] += c;
    }

    private long query(int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }

    // S2: Sort

}