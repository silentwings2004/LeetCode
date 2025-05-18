package LC1801_2100;
import java.util.*;
public class LC2094_Finding3DigitEvenNumbers {
    /**
     * You are given an integer array digits, where each element is a digit. The array may contain duplicates.
     *
     * You need to find all the unique integers that follow the given requirements:
     *
     * The integer consists of the concatenation of three elements from digits in any arbitrary order.
     * The integer does not have leading zeros.
     * The integer is even.
     * For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
     *
     * Return a sorted array of the unique integers.
     *
     * Input: digits = [2,1,3,0]
     * Output: [102,120,130,132,210,230,302,310,312,320]
     *
     * Constraints:
     *
     * 3 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     * @param digits
     * @return
     */
    // time = O(1), space = O(1)
    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int x : digits) cnt[x]++;
        List<Integer> q = new ArrayList<>();
        for (int i = 100; i < 1000; i += 2) {
            int[] w = get(i);
            boolean f = true;
            for (int j = 0; j < 10; j++) {
                if (w[j] > cnt[j]) {
                    f = false;
                    break;
                }
            }
            if (f) q.add(i);
        }
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }

    private int[] get(int x) {
        int[] cnt = new int[10];
        while (x > 0) {
            int y = x % 10;
            x /= 10;
            cnt[y]++;
        }
        return cnt;
    }

    // S2
    public int[] findEvenNumbers2(int[] digits) {
        int n = digits.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int a = digits[i], b = digits[j], c = digits[k];
                    int v1 = a * 100 + b * 10 + c;
                    int v2 = a * 100 + c * 10 + b;
                    int v3 = b * 100 + a * 10 + c;
                    int v4 = b * 100 + c * 10 + a;
                    int v5 = c * 100 + a * 10 + b;
                    int v6 = c * 100 + b * 10 + a;
                    if (v1 >= 100 && v1 % 2 == 0) set.add(v1);
                    if (v2 >= 100 && v2 % 2 == 0) set.add(v2);
                    if (v3 >= 100 && v3 % 2 == 0) set.add(v3);
                    if (v4 >= 100 && v4 % 2 == 0) set.add(v4);
                    if (v5 >= 100 && v5 % 2 == 0) set.add(v5);
                    if (v6 >= 100 && v6 % 2 == 0) set.add(v6);
                }
            }
        }
        int m = set.size();
        int[] res = new int[m];
        int idx = 0;
        for (int x : set) res[idx++] = x;
        Arrays.sort(res);
        return res;
    }
}