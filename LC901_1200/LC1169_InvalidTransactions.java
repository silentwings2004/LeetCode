package LC901_1200;
import java.util.*;
public class LC1169_InvalidTransactions {
    /**
     * A transaction is possibly invalid if:
     *
     * the amount exceeds $1000, or;
     * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
     * You are given an array of strings transaction where transactions[i] consists of comma-separated values
     * representing the name, time (in minutes), amount, and city of the transaction.
     *
     * Return a list of transactions that are possibly invalid. You may return the answer in any order.
     *
     * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
     * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
     *
     * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
     * Output: ["alice,50,1200,mtv"]
     *
     * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
     * Output: ["bob,50,1200,mtv"]
     *
     * Constraints:
     *
     * transactions.length <= 1000
     * Each transactions[i] takes the form "{name},{time},{amount},{city}"
     * Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
     * Each {time} consist of digits, and represent an integer between 0 and 1000.
     * Each {amount} consist of digits, and represent an integer between 0 and 2000.
     * @param transactions
     * @return
     */
    // time = O(n^2), space = O(n)
    public List<String> invalidTransactions(String[] transactions) {
        HashSet<Integer> set = new HashSet<>();
        HashMap<String, List<Node>> map = new HashMap<>();
        int n = transactions.length;
        for (int i = 0; i < n; i++) {
            String t = transactions[i];
            String[] strs = t.split(",");
            String name = strs[0], city = strs[3];
            int ts = Integer.parseInt(strs[1]), amount = Integer.parseInt(strs[2]);
            if (amount > 1000) set.add(i);
            if (map.containsKey(name)) {
                for (Node x : map.get(name)) {
                    if (!x.city.equals(city) && Math.abs(x.ts - ts) <= 60) {
                        set.add(i);
                        set.add(x.id);
                    }
                }
            }
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(new Node(i, ts, amount, city));
        }
        List<String> res = new ArrayList<>();
        for (int i : set) res.add(transactions[i]);
        return res;
    }

    private class Node {
        int id, ts, amount;
        String city;
        public Node(int id, int ts, int amount, String city) {
            this.id = id;
            this.ts = ts;
            this.amount = amount;
            this.city = city;
        }
    }
}