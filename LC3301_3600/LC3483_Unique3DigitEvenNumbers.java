package LC3301_3600;

public class LC3483_Unique3DigitEvenNumbers {
    /**
     * You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even
     * numbers that can be formed using these digits.
     *
     * Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.
     *
     * Input: digits = [1,2,3,4]
     * Output: 12
     *
     * Input: digits = [0,2,2]
     * Output: 2
     *
     * Input: digits = [6,6,6]
     * Output: 1
     *
     * Input: digits = [1,3,5]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= digits.length <= 10
     * 0 <= digits[i] <= 9
     * @param digits
     * @return
     */
    // time = O(n^3), space = O(1)
    public int totalNumbers(int[] digits) {
        int n = digits.length, res = 0;
        boolean[] st = new boolean[1000];
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j || digits[k] % 2 == 1) continue;
                    int x = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (st[x]) continue;
                    st[x] = true;
                    res++;
                }
            }
        }
        return res;
    }
}