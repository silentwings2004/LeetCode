package LC1201_1500;
import java.util.*;
public class LC1346_CheckIfNandItsDoubleExist {
    /**
     * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M
     * ( i.e. N = 2 * M).
     *
     * More formally check if there exists two indices i and j such that :
     *
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     *
     * Input: arr = [10,2,5,3]
     * Output: true
     *
     * Constraints:
     *
     * 2 <= arr.length <= 500
     * -10^3 <= arr[i] <= 10^3
     * @param arr
     * @return
     */
    // time = O(n), space = O(n)
    public boolean checkIfExist(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : arr) {
            if (set.contains(2 * x) || x % 2 == 0 && set.contains(x / 2)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }
}
