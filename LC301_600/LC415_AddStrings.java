package LC301_600;
import java.util.*;
public class LC415_AddStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *
     * Note:
     *
     * The length of both num1 and num2 is < 5100.
     * Both num1 and num2 contains only digits 0-9.
     * Both num1 and num2 does not contain any leading zero.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     *
     * @param num1
     * @param num2
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public String addStrings(String num1, String num2) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        int m = num1.length(), n = num2.length();
        for (int i = m - 1; i >= 0; i--) A.add(num1.charAt(i) - '0');
        for (int i = n - 1; i >= 0; i--) B.add(num2.charAt(i) - '0');
        List<Integer> C = add(A, B);
        StringBuilder sb = new StringBuilder();
        for (int i = C.size() - 1; i >= 0; i--) sb.append(C.get(i));
        return sb.toString();
    }

    private List<Integer> add(List<Integer> A, List<Integer> B) {
        List<Integer> C = new ArrayList<>();
        int m = A.size(), n = B.size();
        for (int i = 0, j = 0, t = 0; i < m || j < n || t > 0; i++, j++) {
            if (i < m) t += A.get(i);
            if (j < n) t += B.get(j);
            C.add(t % 10);
            t /= 10;
        }
        return C;
    }
}