package LC2401_2700;
import java.util.*;
public class LC2521_DistinctPrimeFactorsofProductofArray {
    /**
     * Given an array of positive integers nums, return the number of distinct prime factors in the product of the
     * elements of nums.
     *
     * Note that:
     *
     * A number greater than 1 is called prime if it is divisible by only 1 and itself.
     * An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
     *
     * Input: nums = [2,4,3,7,10,6]
     * Output: 4
     *
     * Input: nums = [2,4,8,16]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 2 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n*sqrt(n)), space = O(n)
    public int distinctPrimeFactors(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.addAll(get_primes(x));
        return set.size();
    }

    private List<Integer> get_primes(int x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= x / i; i++) {
            if (x % i== 0) {
                res.add(i);
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) res.add(x);
        return res;
    }
}