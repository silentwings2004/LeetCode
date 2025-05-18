package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MajorityElement
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 169. Majority Element
 */
public class LC169_MajorityElement {
    /**
     * Given an array of size n, find the majority element. The majority element is the element that appears more than
     * ⌊ n/2 ⌋ times.
     *
     * You may assume that the array is non-empty and the majority element always exist in the array.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: [2,2,1,1,1,2,2]
     * Output: 2
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int majorityElement(int[] nums) {
        int[] counter = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counter[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // 判断当前1的累加值和n/2的大小关系；如果当前位置的1的累加值 > n/2 --> majority element在这个bit位置上一定是1；
            // 如果当前位置的1的累加值 < n/2 --> majority element在这个bit位上一定是0，因为它自己就超过一半了
            if (counter[i] > nums.length / 2) res += 1 << i;
        }
        return res;
    }
}
