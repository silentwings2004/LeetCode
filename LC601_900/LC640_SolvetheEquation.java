package LC601_900;

public class LC640_SolvetheEquation {
    /**
     * Solve a given equation and return the value of 'x' in the form of a string "x=#value". The equation contains
     * only '+', '-' operation, the variable 'x' and its coefficient. You should return "No solution" if there is no
     * solution for the equation, or "Infinite solutions" if there are infinite solutions for the equation.
     *
     * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
     *
     * Input: equation = "x+5-3+x=6+x-2"
     * Output: "x=2"
     *
     * Input: equation = "x=x"
     * Output: "Infinite solutions"
     *
     * Constraints:
     *
     * 3 <= equation.length <= 1000
     * equation has exactly one '='.
     * equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, and the
     * variable 'x'.
     * @param equation
     * @return
     */
    // time = O(n), space = O(1)
    public String solveEquation(String equation) {
        int k = equation.indexOf('=');
        int[] l = helper(equation.substring(0, k));
        int[] r = helper(equation.substring(k + 1));
        int a = l[0] - r[0], b = r[1] - l[1];
        if (a == 0) {
            if (b == 0) return "Infinite solutions";
            return "No solution";
        }
        return "x=" + String.valueOf(b / a);
    }

    private int[] helper(String s) {
        if (s.charAt(0) != '+' && s.charAt(0) != '-') s = "+" + s;
        int n = s.length(), a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && Character.isDigit(s.charAt(j))) j++;
            int c = 1;
            if (i + 1 <= j - 1) c = Integer.parseInt(s.substring(i + 1, j));
            if (s.charAt(i) == '-') c = -c;
            if (j < n && s.charAt(j) == 'x') {
                a += c;
                i = j;
            } else {
                b += c;
                i = j - 1;
            }
        }
        return new int[]{a, b};
    }
}
/**
 * ax + b = cx + d
 * (a-c)x = d - b
 * Ax = B
 * A = 0:
 * 1. B = 0  无穷解
 * 2. B != 0 无解
 * A != 0 => x = B / A
 */