package LC3301_3600;

public class LC3477_FruitsIntoBasketsII {
    /**
     * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the
     * quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
     *
     * From left to right, place the fruits according to these rules:
     *
     * Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the
     * quantity of that fruit type.
     * Each basket can hold only one type of fruit.
     * If a fruit type cannot be placed in any basket, it remains unplaced.
     * Return the number of fruit types that remain unplaced after all possible allocations are made.
     *
     * Input: fruits = [4,2,5], baskets = [3,5,4]
     * Output: 1
     *
     * Input: fruits = [3,6,1], baskets = [6,4,7]
     * Output: 0
     *
     * Constraints:
     *
     * n == fruits.length == baskets.length
     * 1 <= n <= 100
     * 1 <= fruits[i], baskets[i] <= 1000
     * @param fruits
     * @param baskets
     * @return
     */
    // time = O(n^2), space = O(n)
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length, res = 0;
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            boolean f = false;
            for (int j = 0; j < n; j++) {
                if (baskets[j] >= fruits[i] && !st[j]) {
                    st[j] = true;
                    f = true;
                    break;
                }
            }
            if (!f) res++;
        }
        return res;
    }
}