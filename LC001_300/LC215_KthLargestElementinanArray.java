package LC001_300;
import java.util.*;
public class LC215_KthLargestElementinanArray {
    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     *
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * Input: nums = [3,2,1,5,6,4], k = 2
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * @param nums
     * @param k
     * @return
     */
    // S1: minHeap
    // time = O(nlogk), space = O(k)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.poll();
    }

    // S2: quick select
    // time = O(n) on average, O(n^2) worst case, space = O(1)
    public int findKthLargest2(int[] nums, int k) {
        return quick_select(nums, 0, nums.length - 1, k - 1);
    }

    private int quick_select(int[] nums, int l, int r, int k) {
        if (l >= r) return nums[r];

        int x = nums[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            while (nums[++i] > x);
            while (nums[--j] < x);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (k <= j) return quick_select(nums, l, j, k);
        return quick_select(nums, j + 1, r, k);
    }

    // S2.1: Quick Select
    // time = O(n) on average, O(n^2) worst case, space = O(1)
    public int findKthLargest21(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k);
    }

    private int quickselect(int[] nums, int a, int b, int k) {
        int pivot = nums[a + (b - a) / 2];
        int i = a, j = b, t = a;
        while (t <= j) {
            if (nums[t] < pivot) {
                swap(nums, i, t);
                i++;
                t++;
            } else if (nums[t] > pivot) {
                swap(nums, t, j);
                j--;
            } else t++;
        }

        if (b - j >= k) return quickselect(nums, j + 1, b, k);
        if (b - i + 1 >= k) return pivot;
        return quickselect(nums, a, i - 1, k - (b - i + 1));
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // S3: Binary Search
    // time = O(n * logC), space = O(1)
    public int findKthLargest3(int[] nums, int k) {
        int left = Integer.MIN_VALUE / 2, right = Integer.MAX_VALUE / 2;
        while (left < right) { // left = 0, right = 1  最多分32次，就一定能找到任何整数 => logC
            int mid = right - (right - left) / 2;
            if (count(nums, mid) >= k) left = mid; // mid might be the answer，因为mid可能有重复
            else right = mid - 1;
        }
        return left;
    }

    private int count(int[] nums, int t) { // O(n)
        int res = 0;
        for (int x : nums) {
            if (x >= t) res++;
        }
        return res;
    }
}
/**
 * 1. sort the whole array: O(nlogn)
 * 2. PQ: rolling k max elements: O(nlogk)
 * 3. binary search by value
 * guess t:
 * if countNumber(>=t) >= k => adjust bigger
 *                     < k  => adjust smaller
 * 一定有解 => 收敛解就是最终解
 * 我怎么保证收敛解一定是数组里的元素
 * binary search => the largest t  s.t. satisfy condition
 * array => the kth largest element is the largest t  s.t.  satisfy condition
 *
 * Quick Select: k-th element => O(n) on average
 *      pivot
 * x x x o x x x x x
 * s s s o o o l l l
 * ^ ^         ^
 * i t         j
 * 三指针 ref: LC75 sort colors
 * t之前就是small + equal, 而i之前都是small, i 始终指向的都是equal的元素, j 指向large前面的一个元素
 * 处理到t与j相遇为止
 * 作用是什么呢？看有多少个元素small, equal or large
 * s s s o o o l l l
 *  a     b      c
 * if (c >= k) => find k-th largest in [L]
 * else if (b + c >= k) => k-th largest is pivot
 * else => k-(b+c)th largest in [s]
 * recursion
 */
