package LC301_600;

public class LC565_ArrayNesting {
    /**
     * You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].
     *
     * You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
     *
     * The first element in s[k] starts with the selection of the element nums[k] of index = k.
     * The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
     * We stop adding right before a duplicate element occurs in s[k].
     * Return the longest length of a set s[k].
     *
     * Input: nums = [5,4,0,3,1,6,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] < nums.length
     * All the values of nums are unique.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int arrayNesting(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) {
                int j = i, s = 0;
                while (nums[j] != -1) {
                    s++;
                    int next = nums[j];
                    nums[j] = -1;
                    j = next;
                }
                res = Math.max(res, s);
            }
        }
        return res;
    }
}
/**
 * 每个点只有一个入度和一个出度
 * 一定没有链
 * 这样的图必然是若干个环
 * 每个集合就是一个环
 * 本质就是找一个最大的环
 * 从前往后遍历每个元素，遍历到第一个没有遍历过的元素，必然在一个新的环上
 * 从这个点出发，沿着边走，回到自己的时候，环就找到了。
 * 每找一个点标记下，表示遍历过了。
 *
 * 经典图论问题，每个元素看成一个点
 * 从0向5连一条边 => 建图
 * 每个点只有一条出边和入边 => 必然是若干个环，一定没有尾结点
 * 每个集合对应一个环 => 哪个环的长度最大
 * 从前往后遍历，遍历到第一个未被遍历的元素 => 意味着找到一个新的环
 */
