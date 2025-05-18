package LC601_900;

public class LC651_4KeysKeyboard {
    /**
     * Imagine you have a special keyboard with the following keys:
     *
     * A: Print one 'A' on the screen.
     * Ctrl-A: Select the whole screen.
     * Ctrl-C: Copy selection to buffer.
     * Ctrl-V: Print buffer on screen appending it after what has already been printed.
     * Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the
     * keys.
     *
     * Input: n = 3
     * Output: 3
     *
     * Input: n = 7
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n <= 50
     * @param n
     * @return
     */
    // time = O(n^2), space = O(n)
    public int maxA(int n) {
        int[] f = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j <= i - 2; j++) {
                f[i] = Math.max(f[i], f[i - j - 2] * (j + 1));
            }
        }
        return f[n];
    }
}