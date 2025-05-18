package LC3001_3300;
import java.util.*;
public class LC3186_MaximumTotalDamageWithSpellCasting {
    /**
     * A magician has various spells.
     *
     * You are given an array power, where each element represents the damage of a spell. Multiple spells can have the
     * same damage value.
     *
     * It is a known fact that if a magician decides to cast a spell with a damage of power[i], they cannot cast any
     * spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
     *
     * Each spell can be cast only once.
     *
     * Return the maximum possible total damage that a magician can cast.
     *
     * Input: power = [1,1,3,4]
     * Output: 6
     *
     * Input: power = [7,1,6,6]
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= power.length <= 10^5
     * 1 <= power[i] <= 10^9
     * @param power
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        int n = power.length;
        long[] f = new long[n];
        TreeSet<Long> set = new TreeSet<>();
        long res = 0;

        for (int i = 0, j = 0; i < n; i++) {
            if (i > 0 && power[i] == power[i - 1]) f[i] = f[i - 1] + power[i];
            else {
                while (power[i] - power[j] > 2) set.add(f[j++]);
                f[i] = (set.size() > 0 ? set.last() : 0) + power[i];
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long maximumTotalDamage2(int[] power) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : power) map.put(x, map.getOrDefault(x, 0) + 1);
        int n = map.size();
        int[] a = new int[n];
        int idx = 0;
        for (int key : map.keySet()) a[idx++] = key;
        Arrays.sort(a);

        long[] f = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (a[j] < a[i] - 2) j++;
            f[i + 1] = Math.max(f[i], f[j] + 1L * a[i] * map.get(a[i]));
        }
        return f[n];
    }
}
/**
 * dfs(i) = max(dfs(i - 1), dfs(j - 1) + x * cnt[x])
 * f[i] = max(f[i - 1], f[j - 1] + x * cnt[x])
 * i 从 0 到 n - 1
 * f[i + 1] = max(f[i], f[j] + x *cnt[x])
 * f[0] = 0
 * f[i + 1] 就表示 a[0] 到 a[i] 中选伤害值，最大和是多少
 */