package LC3001_3300;

public class LC3134_FindtheMedianoftheUniquenessArray {
    /**
     * You are given an integer array nums. The uniqueness array of nums is the sorted array that contains the number
     * of distinct elements of all the
     * subarrays
     *  of nums. In other words, it is a sorted array consisting of distinct(nums[i..j]), for all 0 <= i <= j <
     *  nums.length.
     *
     * Here, distinct(nums[i..j]) denotes the number of distinct elements in the subarray that starts at index i and
     * ends at index j.
     *
     * Return the median of the uniqueness array of nums.
     *
     * Note that the median of an array is defined as the middle element of the array when it is sorted in
     * non-decreasing order. If there are two choices for a median, the smaller of the two values is taken.
     *
     * Input: nums = [1,2,3]
     * Output: 1
     *
     * Input: nums = [3,4,3,4,5]
     * Output: 2
     *
     * Input: nums = [4,3,5,4]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length, l = 1, r = n;
        long tot = 1L * (1 + n) * n / 2;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, tot, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] nums, long tot, int mid) {
        int n = nums.length, j = 0, t = 0;
        long res = 0;
        int[] cnt = new int[N];
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            if (cnt[nums[i]] == 1) t++;
            while (t > mid) {
                cnt[nums[j]]--;
                if (cnt[nums[j]] == 0) t--;
                j++;
            }
            res += i - j + 1;
        }
        return res * 2 >= tot;
    }
}
/**
 * 中位数本质上也是第k大，第k小问题
 */