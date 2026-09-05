package LC3901_4200;

public class LC4036_LexicographicallyLargestStringAfterPairTransformations {
    /**
     * You are given an integer array nums.
     *
     * For each integer x in nums, start with a string consisting of exactly x lowercase 'a' characters.
     *
     * You may perform the following operation any number of times (including zero):
     *
     * Choose two adjacent equal letters and replace them with the next letter in the alphabet.
     * For example, "aa" can be replaced with "b", and "bb" can be replaced with "c". The pair "zz" cannot be replaced.
     *
     * For each x, determine the lexicographically largest string that can be obtained.
     *
     * Return an array of strings where the ith string is the answer for nums[i].
     *
     * A string a is lexicographically larger than a string b if, at the first position where they differ, a contains a
     * letter that appears later in the alphabet than the corresponding letter in b. If the first
     * min(a.length, b.length) characters are equal, the longer string is lexicographically larger.
     *
     * Input: nums = [2,5,7]
     * Output: ["b","ca","cba"]
     *
     * Input: nums = [3,9,1]
     * Output: ["ba","da","a"]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^8
     * @param nums
     * @return
     */
    // time = O(nlog(max(nums)), space = O(n)
    public String[] largestString(int[] nums) {
        int n = nums.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int x = nums[i], p = 0, v = 1;
            while (v < x) {
                v *= 2;
                p++;
            }
            p = Math.min(25, p);
            for (int j = p; j >= 0; j--) {
                int t = 1 << j;
                sb.append(String.valueOf((char)('a' + j)).repeat(x / t));
                x -= x / t * t;
            }
            res[i] = sb.toString();
        }
        return res;
    }
}
/**
 * 2048 game
 *
 */