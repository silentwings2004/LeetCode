package LC3001_3300;

public class LC3100_WaterBottlesII {
    /**
     * You are given two integers numBottles and numExchange.
     *
     * numBottles represents the number of full water bottles that you initially have. In one operation, you can
     * perform one of the following operations:
     *
     * Drink any number of full water bottles turning them into empty bottles.
     * Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
     * Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange. For example,
     * if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
     *
     * Return the maximum number of water bottles you can drink.
     *
     * Input: numBottles = 13, numExchange = 6
     * Output: 15
     *
     * Input: numBottles = 10, numExchange = 3
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= numBottles <= 100
     * 1 <= numExchange <= 100
     * @param n
     * @param m
     * @return
     */
    // time = O(sqrt(n)), space = O(1)
    public int maxBottlesDrunk(int n, int m) {
        int res = n;
        while (n >= m) {
            n = n - m + 1;
            res++;
            m++;
        }
        return res;
    }
}
/**
 * 1. numBottles
 * 用numExchange个空水瓶 => 一个满水瓶 => 一个空水瓶
 * k ~ sqrt(n)
 * 哪怕出到10^9都没问题，因为是根号复杂度 => 暴力模拟是ok的
 */