package LC3601_3900;

public class LC3723_MaximizeSumofSquaresofDigits {
    /**
     * You are given two positive integers num and sum.
     *
     * A positive integer n is good if it satisfies both of the following:
     *
     * The number of digits in n is exactly num.
     * The sum of digits in n is exactly sum.
     * The score of a good integer n is the sum of the squares of digits in n.
     *
     * Return a string denoting the good integer n that achieves the maximum score. If there are multiple possible
     * integers, return the maximum score. If no such integer exists, return an empty string.
     *
     * Input: num = 2, sum = 3
     * Output: "30"
     *
     * Input: num = 2, sum = 17
     * Output: "98"
     *
     * Input: num = 1, sum = 10
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= num <= 2 * 10^5
     * 1 <= sum <= 2 * 10^6
     * @param num
     * @param sum
     * @return
     */
    // time = O(n), space = O(n)
    public String maxSumOfSquares(int num, int sum) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (sum > 9) {
                sb.append(9);
                sum -= 9;
            } else {
                sb.append(sum);
                sum -= sum;
            }
        }
        return sum == 0 ? sb.toString() : "";
    }
}