package LC2401_2700;

public class LC2598_SmallestMissingNonnegativeIntegerAfterOperations {
    /**
     * You are given a 0-indexed integer array nums and an integer value.
     *
     * In one operation, you can add or subtract value from any element of nums.
     *
     * For example, if nums = [1,2,3] and value = 2, you can choose to subtract value from nums[0] to make nums = [-1,2,3].
     * The MEX (minimum excluded) of an array is the smallest missing non-negative integer in it.
     *
     * For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2.
     * Return the maximum MEX of nums after applying the mentioned operation any number of times.
     *
     * Input: nums = [1,-10,7,13,6,8], value = 5
     * Output: 4
     *
     * Input: nums = [1,-10,7,13,6,8], value = 7
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length, value <= 10^5
     * -109 <= nums[i] <= 10^9
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] st = new int[N];
        for (int x : nums) {
            if (x < 0) {
                int t = (Math.abs(x) + value - 1) / value;
                x += t * value;
            }
            st[x % value]++;
        }

        for (int i = 0; i < n; i++) {
            int t = i % value;
            if (st[t] == 0) return i;
            st[t]--;
        }
        return n;
    }

    // S2
    // time = O(n), space = O(k)
    public int findSmallestInteger2(int[] nums, int value) {
        int[] cnt = new int[value];
        for (int x : nums) cnt[(x % value + value) % value]++;

        int minf = Integer.MAX_VALUE, r = -1;
        for (int i = 0; i < value; i++) {
            if (cnt[i] < minf) {
                minf = cnt[i];
                r = i;
            }
        }
        return value * minf + r;
    }
}
/**
 * 对一个元素无论做多少次的加减操作，不变的就是对value的余数。
 * 只要有元素对value的余数是0，我们就可以构造出0来。
 * 同理，只要有元素对value的余数是1，我们就可以构造出1来。
 * 以此类推，我们可以推出是否能构造出value-1.
 * 假设以上这些都可以构造出来，那么接下来就考虑能否构造出value来呢？
 * 其实只要再来一个能被value整除的元素，我们就可以构造出value来。
 * 同理，只要再有一个元素对value的余数是1，那么我们就可以调整它变成value+1，以此类推。
 * 所以我们将所有元素以对value的模分类，找到其中的最小频次c以及对应的模r，
 * 就意味着我们能构造出c个[0, value-1]的完整周期来，也就是能构造出[0, value*c-1]的所有整数。
 * 接下来再算上零头，我们可以再构造出接下来r个元素。最终未能构造出的MEX就是value*c+r.
 */