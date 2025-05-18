package LC3001_3300;
import java.util.*;
public class LC3074_AppleRedistributionintoBoxes {
    /**
     * You are given an array apple of size n and an array capacity of size m.
     *
     * There are n packs where the ith pack contains apple[i] apples. There are m boxes as well, and the ith box has a
     * capacity of capacity[i] apples.
     *
     * Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.
     *
     * Note that, apples from the same pack can be distributed into different boxes.
     *
     * Input: apple = [1,3,2], capacity = [4,3,1,5,2]
     * Output: 2
     *
     * Input: apple = [5,5,5], capacity = [2,4,2,7]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == apple.length <= 50
     * 1 <= m == capacity.length <= 50
     * 1 <= apple[i], capacity[i] <= 50
     * The input is generated such that it's possible to redistribute packs of apples into boxes.
     * @param apple
     * @param capacity
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int s = 0, res = 0;
        for (int x : apple) s += x;
        int n = capacity.length;
        for (int i = n - 1; i >= 0; i--) {
            s -= capacity[i];
            if (s <= 0) {
                res = n - i;
                break;
            }
        }
        return res;
    }
}