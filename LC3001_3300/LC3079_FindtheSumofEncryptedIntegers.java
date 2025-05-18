package LC3001_3300;

public class LC3079_FindtheSumofEncryptedIntegers {
    /**
     * You are given an integer array nums containing positive integers. We define a function encrypt such that
     * encrypt(x) replaces every digit in x with the largest digit in x. For example, encrypt(523) = 555 and
     * encrypt(213) = 333.
     *
     * Return the sum of encrypted elements.
     *
     * Input: nums = [1,2,3]
     * Output: 6
     *
     * Input: nums = [10,21,31]
     * Output: 66
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int sumOfEncryptedInt(int[] nums) {
        int res = 0;
        for (int x : nums) {
            int t = 0, cnt = 0;
            while (x > 0) {
                t = Math.max(t, x % 10);
                x /= 10;
                cnt++;
            }
            int v = 0;
            for (int i = 0; i < cnt; i++) v = v * 10 + t;
            res += v;
        }
        return res;
    }
}