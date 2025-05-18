package LC601_900;

public class LC860_LemonadeChange {
    /**
     * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at
     * a time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10,
     * or $20 bill. You must provide the correct change to each customer so that the net transaction is that the
     * customer pays $5.
     *
     * Note that you do not have any change in hand at first.
     *
     * Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide
     * every customer with the correct change, or false otherwise.
     *
     * Input: bills = [5,5,5,10,20]
     * Output: true
     *
     * Input: bills = [5,5,10,10,20]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= bills.length <= 10^5
     * bills[i] is either 5, 10, or 20.
     * @param bills
     * @return
     */
    // time = O(n), space = O(1)
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int x : bills) {
            if (x == 5) five++;
            else if (x == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else if (x == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) five -= 3;
                else return false;
            }
        }
        return true;
    }
}