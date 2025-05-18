package LC301_600;
import java.util.*;
public class LC475_Heaters {
    /**
     * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to
     * warm all the houses.
     *
     * Every house can be warmed, as long as the house is within the heater's warm radius range.
     *
     * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so
     * that those heaters could cover all houses.
     *
     * Notice that all the heaters follow your radius standard, and the warm radius will the same.
     *
     * Input: houses = [1,2,3], heaters = [2]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= houses.length, heaters.length <= 3 * 10^4
     * 1 <= houses[i], heaters[i] <= 10^9
     * @param houses
     * @param heaters
     * @return
     */
    // S1
    // time = O((m + n) * logn), space = O(n)
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<>();
        int res = 0;
        for (int heater : heaters) set.add(heater); // O(nlogn)
        for (int house : houses) { // O(m)
            int r = 0;
            Integer fk = set.floor(house);
            Integer ck = set.ceiling(house);
            if (fk != null && ck != null) r = Math.min(house - fk, ck - house);
            else if (fk != null) r = house - fk;
            else r = ck - house;
            res = Math.max(res, r);
        }
        return res;
    }

    // S2: binary search
    // time = O(nlogn), space = O(logn)
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid, houses, heaters)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int mid, int[] houses, int[] heaters) {
        int m = houses.length, n = heaters.length;
        for (int i = 0, j = 0; i < m; i++) {
            while (j < n && Math.abs(heaters[j] - houses[i]) > mid) j++;
            if (j >= n) return false;
        }
        return true;
    }
}
/**
 * 一维分布的heater，离房子左边最接近，右边最接近的Heaters，找一个最小的距离
 * 所有house都有一个最小值，然后找其中的最大值即可。
 * 单调 => 二分 供暖半径 r
 * 供暖范围是个区间 => 给若干个区间，能否覆盖住所有房子
 * 区间合并
 * 直接用双指针 => 对每个房子，从左往右看第一个能否覆盖这个房子的区间，也是单调的关系
 * O(nlogn)
 */
