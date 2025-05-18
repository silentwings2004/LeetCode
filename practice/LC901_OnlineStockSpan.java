package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: OnlineStockSpan
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 901. Online Stock Span
 */
public class LC901_OnlineStockSpan {
    /**
     * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's
     * price for the current day.
     *
     * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and
     * going backwards) for which the price of the stock was less than or equal to today's price.
     *
     * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock
     * spans would be [1, 1, 1, 2, 1, 4, 6].
     *
     *
     *
     * Example 1:
     *
     * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
     * Output: [null,1,1,1,2,1,4,6]
     * Explanation:
     * First, S = StockSpanner() is initialized.  Then:
     * S.next(100) is called and returns 1,
     * S.next(80) is called and returns 1,
     * S.next(60) is called and returns 1,
     * S.next(70) is called and returns 2,
     * S.next(60) is called and returns 1,
     * S.next(75) is called and returns 4,
     * S.next(85) is called and returns 6.
     *
     * Note that (for example) S.next(75) returned 4, because the last 4 prices
     * (including today's price of 75) were less than or equal to today's price.
     *
     *
     * Note:
     *
     * Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
     * There will be at most 10000 calls to StockSpanner.next per test case.
     * There will be at most 150000 calls to StockSpanner.next across all test cases.
     * The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
     */
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public LC901_OnlineStockSpan() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public int next(int price) {
        int count = 1;
        while (!stack1.isEmpty() && price >= stack1.peek()) {
            stack1.pop();
            count += stack2.pop(); // 新的count把之前要准备pop()出来的元素的权重都包括了，所以只需压入这个新元素的权重即可。
        }
        stack1.push(price);
        stack2.push(count);
        return count;
    }
}
