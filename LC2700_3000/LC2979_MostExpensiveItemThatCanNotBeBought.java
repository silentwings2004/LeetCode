package LC2700_3000;
import java.util.*;
public class LC2979_MostExpensiveItemThatCanNotBeBought {
    /**
     * You are given two distinct prime numbers primeOne and primeTwo.
     *
     * Alice and Bob are visiting a market. The market has an infinite number of items, for any positive integer x
     * there exists an item whose price is x. Alice wants to buy some items from the market to gift to Bob. She has an
     * infinite number of coins in the denomination primeOne and primeTwo. She wants to know the most expensive item she
     * can not buy to gift to Bob.
     *
     * Return the price of the most expensive item which Alice can not gift to Bob.
     *
     * Input: primeOne = 2, primeTwo = 5
     * Output: 3
     *
     * Input: primeOne = 5, primeTwo = 7
     * Output: 23
     *
     * Constraints:
     *
     * 1 < primeOne, primeTwo < 10^4
     * primeOne, primeTwo are prime numbers.
     * primeOne * primeTwo < 10^5
     * @param primeOne
     * @param primeTwo
     * @return
     */
    // time = O(1), space = O(1)
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
}