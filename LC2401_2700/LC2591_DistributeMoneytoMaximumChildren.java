package LC2401_2700;

public class LC2591_DistributeMoneytoMaximumChildren {
    /**
     * You are given an integer money denoting the amount of money (in dollars) that you have and another integer
     * children denoting the number of children that you must distribute the money to.
     *
     * You have to distribute the money according to the following rules:
     *
     * All money must be distributed.
     * Everyone must receive at least 1 dollar.
     * Nobody receives 4 dollars.
     * Return the maximum number of children who may receive exactly 8 dollars if you distribute the money according to
     * the aforementioned rules. If there is no way to distribute the money, return -1.
     *
     * Input: money = 20, children = 3
     * Output: 1
     *
     * Input: money = 16, children = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= money <= 200
     * 2 <= children <= 30
     * @param money
     * @param children
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public int distMoney(int money, int children) {
        if (money < children) return -1;

        int l = 0, r = children;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(money, children, mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(int m, int c, int t) {
        int sum = t * 8;
        if (sum > m) return false;
        if (sum == m) return t == c;
        // sum < m
        m -= sum;
        c -= t;
        if (m < c) return false;
        if (m == c) return true;
        // m > c
        if (c == 0 || c == 1 && m == 4) return false;
        return true;
    }

    // S2: Greedy
    // time = O(n), space = O(1)
    public int distMoney2(int money, int children) {
        if (money < children) return -1;
        if (money == 4 && children == 1) return -1;
        if (money > children * 8) return children - 1;

        int d = money - children;
        int k = d / 7, r = d % 7;
        if (r == 3 && children - k == 1) k--;
        return k;
    }
}
/**
 * 首先考虑无解的情况。
 * 当money<children时，不能保证每个人都能分到钱；当children=1且money=4时，只有一个人必然拿到4块钱。这两种情况都是无解。
 * 其他情况下，既然有解，那么每个人必然都拿到了一块钱，
 * 所以我们先扣除这部分“保底”的钱，即令d = money - children。
 * 因为我们希望尽量多地凑出8来，所以我们考察k=d/7，就是最多能分出8块钱的人数，剩余r=d%7块钱无法再凑出一个8块了。
 * 那么是否意味着答案就是k呢？
 * 有一种特殊情况，就是剩余了4块钱且它恰好只分配给最后一个人。
 * 这种情况下是违反规则的，所以我们必须再让渡出一个8块钱的人，与最后一个人混合再打散避免出现4的情况.
 * 这种情况下，真正能分得8块钱的人数就是k-1.
 */