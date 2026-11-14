package LC3901_4200;

public class LC4098_MinimizetheMaximumValueofGCDConnectedArray {
    /**
     * You are given three integers n, index, and value.
     *
     * An integer array nums of length n is called GCD-Connected if:
     *
     * nums[i] > 0 for 0 <= i <= n - 1.
     * nums[i] < nums[i + 1] for 0 <= i <= n - 2.
     * nums[index] = value.
     * gcd(nums[i], nums[i + 1]) > 1 for 0 <= i <= n - 2.
     *
     * Return the minimum possible maximum value, nums[n - 1], among all GCD-Connected arrays nums. If no such array
     * exists, return -1.
     *
     * The term gcd(a, b) denotes the greatest common divisor of a and b.
     *
     * Input: n = 4, index = 2, value = 8
     * Output: 10
     *
     * Input: n = 2, index = 1, value = 3
     * Output: -1
     *
     * Input: n = 2, index = 0, value = 3
     * Output: 6
     *
     * Constraints:
     *
     * 2 <= n <= 10^9
     * 1 <= value <= 10^9
     * 0 <= index <= n - 1
     * @param n
     * @param index
     * @param value
     * @return
     */
    // time = O(sqrt(value)), space = O(1)
    public long minimumMaxValue(int n, int index, int value) {
        if (value == 1) return -1;
        int p = get(value);
        if (value - p - 2 * (index - 1) <= 0) return -1;
        if (index == n - 1) return value;
        return 1L * value + p + 2 * (n - index - 2);
    }

    private int get(int x) {
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return i;
        }
        return x;
    }
}
/**
 * 1. nums 增长的越慢越好
 * 2. 目标：相邻元素之差尽量都是 2
 * 3. 如果 value 是偶数，那么构造一个公差为 2 的等差数列即可
 *    如果 value 是奇数，那么 g 最小是 value 的最小质因子：value = 9 => i-1 -> 9 - 3 = 6
 *    由于奇数的最小质因子是奇数，奇数 - 奇数是偶数，所以 value 左右两侧的数都是偶数，其余元素按照偶数的方法构造（公差为 2 的等差数列）
 * 4. 构造方案：
 *    设 value 的最小质因子为 p
 *    ..., value-p-4, value-p-2, value-p, value, value+p, value+p+2, value+p+4, ...
 *                     idx-2     idx-1     idx    idx+1    idx+2
 */