package LC301_600;

public class LC517_SuperWashingMachines {
    /**
     * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
     *
     * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine
     * to one of its adjacent washing machines at the same time.
     *
     * Given an integer array machines representing the number of dresses in each washing machine from left to right on
     * the line, return the minimum number of moves to make all the washing machines have the same number of dresses.
     * If it is not possible to do it, return -1.
     *
     * Input: machines = [1,0,5]
     * Output: 3
     *
     * Input: machines = [0,3,0]
     * Output: 2
     *
     * Input: machines = [0,2,0]
     * Output: -1
     *
     * Constraints:
     *
     * n == machines.length
     * 1 <= n <= 10^4
     * 0 <= machines[i] <= 10^5
     * @param machines
     * @return
     */
    // time = O(n), space = O(1)
    public int findMinMoves(int[] machines) {
        int sum = 0, n = machines.length;
        for (int x : machines) sum += x;
        if (sum % n != 0) return -1;
        int avg = sum / n, left = 0, right = sum, res = 0;
        for (int i = 0; i < n; i++) {
            right -= machines[i];
            if (i > 0) left += machines[i - 1];
            int l = Math.max(avg * i - left, 0);
            int r = Math.max(avg * (n - i - 1) - right, 0);
            res = Math.max(res, l + r);
        }
        return res;
    }
}
/**
 * n | sum
 * 贪心问题
 * 每步操作只能弥补左边的一个空缺或者右边的一个空缺
 * 操作数 >= left + right
 *
 * 操作数 >= left + right，因为每步操作我们只能弥补左边的一个空缺，或者右边的一个空缺
 * 注意：left, right都是 > 0
 *
 * 答案的下界：max(left[i] + right[i])
 * 有没有可能取到下界？
 *
 * 考虑取到最大值的洗衣机，是不是一定不可能是空的呢？
 * left + right > 0 （如果不是所有洗衣机都是空的话), 不妨设右边缺货，
 * 如果一个空的洗衣机取到最大值的话，不妨设这个空的洗衣机左边有个不空的洗衣机(右边类似)，意味着左边不缺货(左边=0),找到左边第一个衣服数量 > 0
 * 的洗衣机，每把一个空的洗衣机划到右边，右边缺的就会更多，因此如果说取到最大值的洗衣机是空的话，那么对于这个左边不是0的洗衣机，
 * 那么它右边缺货的数量一定会比这台空的洗衣机更多，意味着取到最大值的洗衣机一定不是这台空的洗衣机，而是左边这台非空的洗衣机 => 矛盾
 *
 * 结论：最后取到max(left + right)的洗衣机，其衣服数量一定不可能是0，一定是 > 0的
 *
 * 那么就看下，如果左边缺货，就把这个洗衣机里的衣服移到左边去，右边同样，每次操作完之后，取最大值的洗衣机，最大值必然-1，因为会满足一边的需求，
 * 最多操作最大值次数，所有最大值就会变为0。
 */