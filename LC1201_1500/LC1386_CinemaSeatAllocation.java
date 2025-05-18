package LC1201_1500;
import java.util.*;
public class LC1386_CinemaSeatAllocation {
    /**
     * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as
     * shown in the figure above.
     *
     * Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] =
     * [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
     *
     * Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies
     * four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be
     * adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle
     * split a four-person group in the middle, which means to have two people on each side.
     *
     * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
     * Output: 4
     *
     * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 1 <= reservedSeats.length <= min(10*n, 10^4)
     * reservedSeats[i].length == 2
     * 1 <= reservedSeats[i][0] <= n
     * 1 <= reservedSeats[i][1] <= 10
     * All reservedSeats[i] are distinct.
     * @param n
     * @param reservedSeats
     * @return
     */
    // time = O(n), space = O(n)
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int x = seat[0], y = seat[1];
            if (y != 1 && y != 10) { // 占用座位1和10等同于空行，map里只记录对分配座位有影响的行！
                int v = map.getOrDefault(x, 0);
                v |= 1 << y - 2; // 2 ~ 9 映射到 0 ~ 7 -> 7位2进制数
                map.put(x, v);
            }
        }

        int left = 0b11110000, right = 0b00001111, mid = 0b00111100;
        int res = n * 2 - map.size() * 2;
        for (int v : map.values()) {
            if ((v & left) == 0 || (v & right) == 0 || (v & mid) == 0) res++;
        }
        return res;
    }
}
/**
 * 一共只有3种安排方法
 * n很大，不能用n来做
 * 用补集的思想来考虑
 * 先假定每一排都是空的，最多安排2n组
 * 2n - 2m
 * 再去判断每一个被占用的排还能放几组
 * 23456789没占用的话，和空的没区别，可以放2组
 * 否则最多只能放1组，只需要判断能否放一组，4567，2345，6789，看是否有组为空
 */
