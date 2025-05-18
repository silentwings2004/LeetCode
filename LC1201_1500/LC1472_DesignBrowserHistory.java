package LC1201_1500;
import java.util.*;
public class LC1472_DesignBrowserHistory {
    /**
     * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the
     * history number of steps or move forward in the history number of steps.
     *
     * Implement the BrowserHistory class:
     *
     * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
     * void visit(string url) Visits url from the current page. It clears up all the forward history.
     * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x,
     * you will return only x steps. Return the current url after moving back in history at most steps.
     * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and
     * steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
     *
     * Input:
     * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
     * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
     * Output:
     * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
     *
     * Constraints:
     *
     * 1 <= homepage.length <= 20
     * 1 <= url.length <= 20
     * 1 <= steps <= 100
     * homepage and url consist of  '.' or lower case English letters.
     * At most 5000 calls will be made to visit, back, and forward.
     * @param homepage
     */
    // time = O(n), space = O(n)
    List<String> q;
    int idx, t;
    public LC1472_DesignBrowserHistory(String homepage) {
        q = new ArrayList<>();
        q.add(homepage);
        idx = 0;
        t = 0;
    }

    public void visit(String url) {
        if (idx == q.size() - 1) q.add(url);
        else q.set(idx + 1, url);
        idx++;
        t = idx;
    }

    public String back(int steps) {
        idx = Math.max(idx - steps, 0);
        return q.get(idx);
    }

    public String forward(int steps) {
        idx = Math.min(idx + steps, t);
        return q.get(idx);
    }
}