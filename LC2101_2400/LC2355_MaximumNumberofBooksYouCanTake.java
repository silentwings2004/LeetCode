package LC2101_2400;
import java.util.*;
public class LC2355_MaximumNumberofBooksYouCanTake {
    /**
     * You are given a 0-indexed integer array books of length n where books[i] denotes the number of books on the ith
     * shelf of a bookshelf.
     *
     * You are going to take books from a contiguous section of the bookshelf spanning from l to r where 0 <= l <= r < n.
     * For each index i in the range l <= i < r, you must take strictly fewer books from shelf i than shelf i + 1.
     *
     * Return the maximum number of books you can take from the bookshelf.
     *
     * Input: books = [8,5,2,7,9]
     * Output: 19
     *
     * Input: books = [7,0,3,4,5]
     * Output: 12
     *
     * Input: books = [8,2,3,7,3,4,0,1,4,3]
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= books.length <= 10^5
     * 0 <= books[i] <= 10^5
     * @param books
     * @return
     */
    // time = O(n), space = O(n)
    public long maximumBooks(int[] books) {
        int n = books.length;
        long[] f = new long[n];
        Stack<Integer> stack = new Stack<>();
        long res = 0;

        for (int i = 0; i < n; i++) {
            if (books[i] == 0) stack.push(i);
            else {
                while (!stack.isEmpty() && books[stack.peek()] >= books[i] - (i - stack.peek())) stack.pop();
                int j = stack.isEmpty() ? -1 : stack.peek();
                f[i] += j == -1 ? 0 : f[j];
                if (books[i] - (i - j) + 1 < 0) f[i] += (0L + books[i]) * (books[i] + 1) / 2;
                else f[i] += (books[i] + books[i] - (i - j) + 1L) * (i - j) / 2;
                stack.push(i);
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
/**
 * dp[i]: the maximum number of books you can take from an subarray ending at i
 * 找一个左边界 => 严格递增
 * x x x x [x 3 9 9 i] x
 *            j     i
 *            3 7 8 9
 * dp[i] = dp[j] + sum{deng cha shu lie}
 * L = i - j
 * (books[i] + books[i] - L + 1) * L / 2
 * dp[i] = dp[j] + sum
 * stack => 
 */