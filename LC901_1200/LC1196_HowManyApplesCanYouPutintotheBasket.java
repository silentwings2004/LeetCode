package LC901_1200;

public class LC1196_HowManyApplesCanYouPutintotheBasket {
    /**
     * You have some apples and a basket that can carry up to 5000 units of weight.
     *
     * Given an integer array weight where weight[i] is the weight of the ith apple, return the maximum number of apples
     * you can put in the basket.
     *
     * Input: weight = [100,200,150,1000]
     * Output: 4
     *
     * Input: weight = [900,950,800,1000,700,800]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= weight.length <= 10^3
     * 1 <= weight[i] <= 10^3
     * @param weight
     * @return
     */
    // time = O(n + w), space = O(w)
    public int maxNumberOfApples(int[] weight) {
        int[] bucket = new int[1001];
        for (int x : weight) bucket[x]++;
        int res = 0, s = 5000;
        for (int i = 1; i <= 1000; i++) {
            if (bucket[i] > 0) {
                if (i * bucket[i] <= s) {
                    res += bucket[i];
                    s -= i * bucket[i];
                } else {
                    res += s / i;
                    break;
                }
            }
        }
        return res;
    }
}