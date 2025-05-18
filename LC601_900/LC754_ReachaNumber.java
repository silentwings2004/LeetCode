package LC601_900;

public class LC754_ReachaNumber {
    /**
     * You are standing at position 0 on an infinite number line. There is a destination at position target.
     *
     * You can make some number of moves numMoves so that:
     *
     * On each move, you can either go left or right.
     * During the ith move (starting from i == 1 to i == numMoves), you take i steps in the chosen direction.
     * Given the integer target, return the minimum number of moves required (i.e., the minimum numMoves) to reach the
     * destination.
     *
     * Input: target = 2
     * Output: 3
     *
     * Input: target = 3
     * Output: 2
     *
     * Constraints:
     *
     * -10^9 <= target <= 10^9
     * target != 0
     * @param target
     * @return
     */
    // time = O(sqrt(n)), space = O(1)
    public int reachNumber(int target) {
        target = Math.abs(target);
        int n = 0, sum = 0;
        while (sum < target || (sum - target) % 2 != 0) {
            n++;
            sum += n;
        }
        return n;
    }
}
/**
 * 1+2+3+...+n = sum >= T
 * sum - 2 * (a1+a2+...+ak) = T
 * a1+...+ak = (sum - T) / 2  整数
 * 1. Sum 与 T 的奇偶性相同
 * 2. Sum >= T
 */