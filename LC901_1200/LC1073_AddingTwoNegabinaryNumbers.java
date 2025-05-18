package LC901_1200;
import java.util.*;
public class LC1073_AddingTwoNegabinaryNumbers {
    /**
     * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
     *
     * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant
     * bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array,
     * format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
     *
     * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
     *
     * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
     * Output: [1,0,0,0,0]
     *
     * Input: arr1 = [0], arr2 = [0]
     * Output: [0]
     *
     * Input: arr1 = [0], arr2 = [1]
     * Output: [1]
     *
     * Constraints:
     *
     * 1 <= arr1.length, arr2.length <= 1000
     * arr1[i] and arr2[i] are 0 or 1
     * arr1 and arr2 have no leading zeros
     * @param arr1
     * @param arr2
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length;
        List<Integer> C = new ArrayList<>();
        for (int i = m - 1, j = n - 1, a = 0, b = 0, c = 0; i >= 0 || j >= 0 || a > 0 || b > 0; i--, j--) {
            if (a == 1 && b == 2) a = b = 0;
            c = b;
            b = a;
            a = 0;
            if (i >= 0) c += arr1[i];
            if (j >= 0) c += arr2[j];
            C.add(c & 1);
            c >>= 1;
            a += c;
            b += c;
        }
        while (C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        Collections.reverse(C);
        int[] res = new int[C.size()];
        for (int i = 0; i < C.size(); i++) res[i] = C.get(i);
        return res;
    }
}
/**
 * (-2)^k + (-2)^k = 2*(-2)^k = -(-2)^(k+1)
 * -1 = -2 + 1
 * => -2*(-2)^(k+1) + (-2)^(k+1) = (-2)^(k+2)+(-2)^(k+1)
 */