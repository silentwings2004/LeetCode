package LC1501_1800;

public class LC1634_AddTwoPolynomialsRepresentedasLinkedLists {
    /**
     * A polynomial linked list is a special type of linked list where every node represents a term in a polynomial
     * expression.
     *
     * Each node has three attributes:
     *
     * coefficient: an integer representing the number multiplier of the term. The coefficient of the term 9x4 is 9.
     * power: an integer representing the exponent. The power of the term 9x4 is 4.
     * next: a pointer to the next node in the list, or null if it is the last node of the list.
     * For example, the polynomial 5x3 + 4x - 7 is represented by the polynomial linked list illustrated below:
     *
     * The polynomial linked list must be in its standard form: the polynomial must be in strictly descending order by
     * its power value. Also, terms with a coefficient of 0 are omitted.
     *
     * Given two polynomial linked list heads, poly1 and poly2, add the polynomials together and return the head of the
     * sum of the polynomials.
     *
     * PolyNode format:
     *
     * The input/output format is as a list of n nodes, where each node is represented as its [coefficient, power]. For
     * example, the polynomial 5x^3 + 4x - 7 would be represented as: [[5,3],[4,1],[-7,0]].
     *
     * Input: poly1 = [[1,1]], poly2 = [[1,0]]
     * Output: [[1,1],[1,0]]
     *
     * Input: poly1 = [[2,2],[4,1],[3,0]], poly2 = [[3,2],[-4,1],[-1,0]]
     * Output: [[5,2],[2,0]]
     *
     * Input: poly1 = [[1,2]], poly2 = [[-1,2]]
     * Output: []
     *
     * Constraints:
     *
     * 0 <= n <= 10^4
     * -109 <= PolyNode.coefficient <= 10^9
     * PolyNode.coefficient != 0
     * 0 <= PolyNode.power <= 10^9
     * PolyNode.power > PolyNode.next.power
     * @param poly1
     * @param poly2
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode dummy = new PolyNode(0, 0), p = dummy;
        PolyNode h1 = poly1, h2 = poly2;
        while (h1 != null && h2 != null) {
            if (h1.power > h2.power) {
                p = p.next = h1;
                h1 = h1.next;
            } else if (h1.power < h2.power) {
                p = p.next = h2;
                h2 = h2.next;
            } else {
                int t = h1.coefficient + h2.coefficient;
                if (t != 0) p = p.next = new PolyNode(t, h1.power);
                h1 = h1.next;
                h2 = h2.next;
            }
        }
        p.next = null;
        if (h1 != null) p.next = h1;
        if (h2 != null) p.next = h2;
        return dummy.next;
    }

    class PolyNode {
        int coefficient, power;
        PolyNode next = null;
        PolyNode() {}
        PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
        PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
    }
}