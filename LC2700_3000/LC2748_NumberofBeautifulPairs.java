package LC2700_3000;

public class LC2748_NumberofBeautifulPairs {
    /**
     * You are given a 0-indexed integer array nums. A pair of indices i, j where 0 <= i < j < nums.length is called
     * beautiful if the first digit of nums[i] and the last digit of nums[j] are coprime.
     *
     * Return the total number of beautiful pairs in nums.
     *
     * Two integers x and y are coprime if there is no integer greater than 1 that divides both of them. In other words,
     * x and y are coprime if gcd(x, y) == 1, where gcd(x, y) is the greatest common divisor of x and y.
     *
     * Input: nums = [2,5,1,4]
     * Output: 5
     *
     * Input: nums = [11,21,12]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 9999
     * nums[i] % 10 != 0
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(1)
    public int countBeautifulPairs(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String s = String.valueOf(nums[i]), t = String.valueOf(nums[j]);
                int a = s.charAt(0) - '0', b = t.charAt(t.length() - 1) - '0';
                if (gcd(a, b) == 1) res++;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2
    // time = O(n), space = O(1)
    public int countBeautifulPairs2(int[] nums) {
        int res = 0;
        int n = nums.length;
        int[] cnt = new int[11];
        for (int x : nums) {
            for (int y = 1; y <= 10; y++) {
                if (gcd(x % 10, y) == 1) res += cnt[y];
            }
            while (x >= 10) x /= 10;
            cnt[x]++;
        }
        return res;
    }
}