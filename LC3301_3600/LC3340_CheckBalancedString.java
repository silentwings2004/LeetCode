package LC3301_3600;

public class LC3340_CheckBalancedString {
    /**
     * You are given a string num consisting of only digits. A string of digits is called balanced if the sum of the
     * digits at even indices is equal to the sum of digits at odd indices.
     *
     * Return true if num is balanced, otherwise return false.
     *
     * Input: num = "1234"
     * Output: false
     *
     * Input: num = "24123"
     * Output: true
     *
     * Constraints:
     *
     * 2 <= num.length <= 100
     * num consists of digits only
     * @param num
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isBalanced(String num) {
        int n = num.length(), odd = 0, even = 0;
        for (int i = 0; i < n; i++) {
            int x = num.charAt(i) - '0';
            if (i % 2 == 0) even += x;
            else odd += x;
        }
        return odd == even;
    }
}