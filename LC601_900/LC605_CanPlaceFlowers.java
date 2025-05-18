package LC601_900;

public class LC605_CanPlaceFlowers {
    /**
     * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be
     * planted in adjacent plots.
     *
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an
     * integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
     *
     * Input: flowerbed = [1,0,0,0,1], n = 1
     * Output: true
     *
     * Input: flowerbed = [1,0,0,0,1], n = 2
     * Output: false
     *
     * Constraints:
     *
     * 1 <= flowerbed.length <= 2 * 10^4
     * flowerbed[i] is 0 or 1.
     * There are no two adjacent flowers in flowerbed.
     * 0 <= n <= flowerbed.length
     * @param flowerbed
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) continue;
            if (i > 0 && flowerbed[i - 1] == 1) continue;
            if (i + 1 < m && flowerbed[i + 1] == 1) continue;
            flowerbed[i] = 1;
            n--;
        }
        return n <= 0;
    }

    // S2: 找规律
    // time = O(n), space = O(1)
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (n == 0) return true;
        int m = flowerbed.length, res = 0;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) continue;
            int j = i;
            while (j < m && flowerbed[j] == 0) j++;
            int k = j - i - 1;
            if (i == 0) k++;
            if (j == m) k++;
            res += k / 2;
            if (res >= n) return true;
            i = j;
        }
        return false;
    }
}
/**
 * 内部：(k-1)/2
 * 有一遍是边界: k/2
 * 两遍都是边界: (k+1)/2
 */