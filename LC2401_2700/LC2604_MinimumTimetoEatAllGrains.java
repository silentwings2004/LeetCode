package LC2401_2700;
import java.util.*;
public class LC2604_MinimumTimetoEatAllGrains {
    /**
     * There are n hens and m grains on a line. You are given the initial positions of the hens and the grains in two
     * integer arrays hens and grains of size n and m respectively.
     *
     * Any hen can eat a grain if they are on the same position. The time taken for this is negligible. One hen can also
     * eat multiple grains.
     *
     * In 1 second, a hen can move right or left by 1 unit. The hens can move simultaneously and independently of each
     * other.
     *
     * Return the minimum time to eat all grains if the hens act optimally.
     *
     * Input: hens = [3,6,7], grains = [2,4,7,9]
     * Output: 2
     *
     * Input: hens = [4,6,109,111,213,215], grains = [5,110,214]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= hens.length, grains.length <= 2*10^4
     * 0 <= hens[i], grains[j] <= 10^9
     * @param hens
     * @param grains
     * @return
     */
    // time = O(n * logn * logn), space = O(logn)
    public int minimumTime(int[] hens, int[] grains) {
        Arrays.sort(hens);
        Arrays.sort(grains);
        long l = 0, r = Integer.MAX_VALUE;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(hens, grains, mid)) r = mid;
            else l = mid + 1;
        }
        return (int)r;
    }

    private boolean check(int[] a, int[] b, long mid) {
        int idx = 0, n = b.length;
        for (int x : a) {
            long l = b[idx], r = 0;
            if (l < x) {
                if (x - l > mid) return false; // 到达不了最左边，由于已经排序了，肯定无法到达，直接返回false
                long d = Math.max((mid - (x - l)) / 2, mid - (x - l) * 2); // 先去左边回到右边还是先去右边再回到左边，取最大值
                r = x + d;
            } else r = x + mid; // 都在右边，则只要去右边即可
            idx = helper(b, r); // 找到第一个 > r 的下一个位置
            if (idx == n) return true; // 走到底了，直接返回true
        }
        return false;
    }

    private int helper(int[] b, long t) {
        int l = 0, r = b.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (b[mid] > t) r = mid;
            else l = mid + 1;
        }
        return b[r] > t ? r : r + 1;
    }

    // S2
    // time = O(nlogn + mlogm), space = O(logn + logm)
    public int minimumTime2(int[] hens, int[] grains) {
        Arrays.sort(hens);
        Arrays.sort(grains);

        int l = 0, r = (int)2e9;
        while (l < r) {
            int mid = (int)((long)l + r >> 1);
            if (check(hens, grains, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] hens, int[] grains, int mid) {
        int n = hens.length, m = grains.length;
        for (int i = 0, j = 0; i < n; i++) {
            int t = 0;
            if (grains[j] < hens[i]) {
                t = hens[i] - grains[j];
                if (t > mid) return false;
            } else t = 0;

            while (j < m && grains[j] <= hens[i]) j++;
            if (mid > 3 * t) {
                while (j < m && grains[j] - hens[i] + t * 2 <= mid) j++;
            } else {
                while (j < m && 2 * (grains[j] - hens[i]) + t <= mid) j++;
            }
            if (j == m) return true;
        }
        return false;
    }
}
/**
 * 分成m份，因为同时出动 => 最大距离最短
 * 每个母鸡在时间T内如何尽量的吃谷子
 *
 */