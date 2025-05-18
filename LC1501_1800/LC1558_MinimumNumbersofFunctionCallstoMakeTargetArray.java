package LC1501_1800;

public class LC1558_MinimumNumbersofFunctionCallstoMakeTargetArray {
    /**
     * You are given an integer array nums. You have an integer array arr of the same length with all values set to 0
     * initially. You also have the following modify function:
     *
     * You want to use the modify function to convert arr to nums using the minimum number of calls.
     *
     * Return the minimum number of function calls to make nums from arr.
     *
     * The test cases are generated so that the answer fits in a 32-bit signed integer.
     *
     * You want to use the modify function to convert arr to nums using the minimum number of calls.
     *
     * Return the minimum number of function calls to make nums from arr.
     *
     * The test cases are generated so that the answer fits in a 32-bit signed integer.
     *
     * Input: nums = [1,5]
     * Output: 5
     *
     * Input: nums = [2,2]
     * Output: 3
     *
     * Input: nums = [4,2,5]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(1)
    public int minOperations(int[] nums) {
        int a = 0, b = 0; // a: count of +1, b: max count of multiple 2
        for (int x : nums) {
            if (x == 0) continue;
            int c = 0;
            while (x > 1) {
                while (x % 2 == 0) {
                    x /= 2;
                    c++;
                }
                if (x > 1) {
                    x--;
                    a++;
                }
            }
            a++;
            b = Math.max(b, c);
        }
        return a + b;
    }

    // S2
    // time = O(nlogn), space = O(1)
    public int minOperations2(int[] nums) {
        int k = 0, cnt = 0;
        for (int x : nums) {
            int bits = 0, ones = 0;
            while (x > 0) {
                if ((x & 1) == 1) ones++;
                bits++;
                x >>= 1;
            }
            k = Math.max(k, bits);
            cnt += ones;
        }
        return Math.max(0, k + cnt - 1);
    }
}
/**
 * 这题可以理解为，在全体不断乘二的过程中，选择是否给某一个数单独加一。
 * 所以就不难联想到二进制了，因为二进制的乘二是左移一位，加一正好是把末尾变成 1。
 * 不难发现，每个数的二进制数的一是必须手动添加的，每个数都得把1给加上。
 * 所以第一步，统计所有数的二进制数中 1 的个数。
 * 具体移位（也就是乘二）的次数，是由最大的数来决定的，比它小的数，只要在合适的时机跟着最大的数变化就好了。
 * 所以，我们只需要维护一个最大值，来计算它的移位次数，也就它的二进制有几位。
 * 但是由于肯定最开始给它加了 1，才能移位（最高位不能为 0），所以移位次数是它位数 -1，最后返回时减了 1。
 */