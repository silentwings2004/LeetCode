package LC2401_2700;

public class LC2505_BitwiseORofAllSubsequenceSums {
    /**
     * Given an integer array nums, return the value of the bitwise OR of the sum of all possible subsequences in the
     * array.
     *
     * A subsequence is a sequence that can be derived from another sequence by removing zero or more elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [2,1,0,3]
     * Output: 7
     *
     * Input: nums = [0,0,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    public long subsequenceSumOr(int[] nums) {
        int md = 0;
        long sum = 0;
        int[] cnt = new int[52];
        for (int x : nums) sum += x;
        for (int i = 52; i >= 0; i--) {
            if ((sum >> i & 1) == 1) {
                md = i;
                break;
            }
        }

        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x >> i & 1) == 1) cnt[i]++;
            }
        }

        for (int i = 0; i < md; i++) {
            if (cnt[i] >= 2) {
                int x = cnt[i] / 2;
                int j = 1;
                while (x > 0) {
                    cnt[i + j]++;
                    x /= 2;
                    j++;
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= md; i++) {
            if (cnt[i] > 0) res |= 1L << i;
        }
        return res;
    }
}
