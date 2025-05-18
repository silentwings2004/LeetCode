package LC301_600;

public class LC457_CircularArrayLoop {
    /**
     * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of
     * indices forward/backward you must move if you are located at index i:
     *
     * If nums[i] is positive, move nums[i] steps forward, and
     * If nums[i] is negative, move nums[i] steps backward.
     * Since the array is circular, you may assume that moving forward from the last element puts you on the first
     * element, and moving backwards from the first element puts you on the last element.
     *
     * A cycle in the array consists of a sequence of indices seq of length k where:
     *
     * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1]
     * -> seq[0] -> ...
     * Every nums[seq[j]] is either all positive or all negative.
     * k > 1
     * Return true if there is a cycle in nums, or false otherwise.
     *
     * Input: nums = [2,-1,1,2,2]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * -1000 <= nums[i] <= 1000
     * nums[i] != 0
     *
     * Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean circularArrayLoop(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            int slow = i, fast = getNext(nums, i);
            // 判断非零且方向相同
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[getNext(nums, fast)] > 0) {
                if (slow == fast) {  //当快慢指针重合的时候，即成环的时候
                    if (slow != getNext(nums, slow)) return true; //避免循环长度等于1的情况
                    else break;
                }
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
            }
            //走过的链条不用再走，因此设置为0
            int add = i;
            while (nums[add] * nums[getNext(nums, add)] > 0) {
                int temp = add;
                add = getNext(nums, add);
                nums[temp] = 0;
            }
        }
        return false;
    }

    private int getNext(int[] nums, int i) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n; // 保证返回值在 [0,n) 中
    }

    // S2
    // time = O(n), space = O(1)
    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length, base = 10000;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= base) continue;
            int k = i, s = base + i, last = -1, t = nums[k] > 0 ? 1 : 0;
            do {
                int p = ((k + nums[k]) % n + n) % n;
                last = nums[k];
                nums[k] = s;
                k = p;
            } while (k != i && nums[k] < s && (t ^ (nums[k] > 0 ? 1 : 0)) == 0);
            if (last % n != 0 && nums[k] == s) return true;
        }
        return false;
    }
}
/**
 * 把相邻两点符号相反的边都删掉 => 转化成一个经典问题， 在图中找环
 * 每个点最多只有一条出边
 * 一旦进入某个环之后，就再也不可能出去了
 * 没有空间限制的话，做普通的图的遍历即可
 * 1. 没有出边
 * 2. 重复点：本次遍历到的点；前面遍历到的点
 *
 * 由于往回跳的不算，所以可以想成，如果一个跳跃的前一个数和后一个数的符号是不同的，那么这条边是不存在的。
 * 这样问题就变成了在一个图里求有没有环路的问题，并且每个点只有一条出边。
 * 所以这个图里面的环路，一定是从某个点出发进入某个环，一旦进入环之后就一直在环里转，而不可能再跳出去了。
 * 如果没有空间限制，就直接给遍历到的点标记一下。如果遍历过程中遍历到了本次遍历到的点，那么就说明有环了。这样就是一个O(n)的做法了。
 * 由于这题要O(1)的空间，然后每个点的数值又很小，所以可以为遍历过的点打上10000+c的标记，其中c标识当前遍历的次数。
 * 这样就能判断是不是本次遍历过的点，或者是之前遍历过的点，或者是没遍历过的点了。
 * 题目还要求环的长度大于1，所以还要判断一下自环的情况。可以通过判断一个点的数字对数组长度是不是模余0的来判断是不是自环。
 */