package LC001_300;
import java.util.*;
public class LC164_MaximumGap {
    /**
     * Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
     * If the array contains less than two elements, return 0.
     *
     * Input: nums = [3,6,9,1]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 10^9
     *
     *
     * Follow up: Could you solve it in linear time/space?
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maximumGap(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        if (n < 2 || min == max) return 0;

        Range[] r = new Range[n - 1];
        for (int i = 0; i < n - 1; i++) r[i] = new Range();
        int len = (max - min + n - 2) / (n - 1);
        for (int x : nums) {
            if (x == min) continue;
            int k = (x - min - 1) / len; // 区间数
            r[k].used = true;
            r[k].min = Math.min(r[k].min, x);
            r[k].max = Math.max(r[k].max, x);
        }

        int res = 0;
        for (int i = 0, last = min; i < n - 1; i++) {
            if (r[i].used) {
                res = Math.max(res, r[i].min - last);
                last = r[i].max;
            }
        }
        return res;
    }

    private class Range {
        private int min, max;
        private boolean used;
        public Range() {
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
            this.used = false;
        }
    }

    // S2: bucket sort
    // time = O(n), space = O(n)
    public static int maximumGap2(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        // step 1: find min and max of the array
        int len = nums.length, min = nums[0], max = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        // step 2: find out the min and max values of each bucket
        // calculate the gap between buckets，n numbers have n - 1 gaps
        int gap = (int)Math.ceil((double)(max - min) / (len - 1));
        int[] bucketsMin = new int[len - 1]; // the min value of each bucket
        int[] bucketsMax = new int[len - 1]; // the max value of each bucket
        Arrays.fill(bucketsMax, Integer.MIN_VALUE); // init the min value of each bucket
        Arrays.fill(bucketsMin, Integer.MAX_VALUE); // init the max value of each bucket

        for (int num : nums) {
            if (num == min || num == max) continue; // if num is at both ends, then it won't fall into any bucket
            int bucket = (num - min) / gap; // calculate which bucket num will fall into
            // after figuring out which bucket to fill num, we update the min and max of that bucket
            bucketsMin[bucket] = Math.min(num, bucketsMin[bucket]);
            bucketsMax[bucket] = Math.max(num, bucketsMax[bucket]);
        }

        // step 3: find out the biggest difference between each bucket
        int res = 0, pre = min; // pre 记录上一个桶的max value，在起点位置时就是min
        for (int i = 0; i < len - 1; i++) {
            if (bucketsMin[i] == Integer.MAX_VALUE && bucketsMax[i] == Integer.MIN_VALUE) continue; // 空桶，直接pass
            res = Math.max(res, bucketsMin[i] - pre);
            pre = bucketsMax[i];
        }
        res = Math.max(res, max - pre); // 记得处理最后的终点，看是否有更大的gap
        return res;
    }
}
/**
 * 先将min, max之间分成若干个区间
 * 将所有数放到对应的区间里
 * 每个小区间内部的两个值之间的差值不可能是答案 => 只要求每个区间的最小值和最大值即可
 * (n-1)*(x-1) <= max - min - 1
 * x-1 <= (max-min-1)/(n-1)
 * x <= (max-min+n-2)/(n-1) = (max-min)/(n-1) 上取整
 */