package LC901_1200;

public class LC1095_FindinMountainArray {
    /**
     * (This problem is an interactive problem.)
     *
     * You may recall that an array arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such
     * an index does not exist, return -1.
     *
     * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
     *
     * MountainArray.get(k) returns the element of the array at index k (0-indexed).
     * MountainArray.length() returns the length of the array.
     * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that
     * attempt to circumvent the judge will result in disqualification.
     *
     * Input: array = [1,2,3,4,5,3,1], target = 3
     * Output: 2
     *
     * Input: array = [0,1,2,4,2,1], target = 3
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= mountain_arr.length() <= 10^4
     * 0 <= target <= 10^9
     * 0 <= mountain_arr.get(index) <= 10^9
     * @param target
     * @param mountainArr
     * @return
     */
    // time = O(logn), space = O(1)
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) r = mid;
            else l = mid + 1;
        }
        int M = r;

        l = 0;
        while (l < r) {
            int mid = l + r >> 1;
            if (mountainArr.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        if (mountainArr.get(r) == target) return r;

        l = M + 1;
        r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (mountainArr.get(mid) <= target) r = mid;
            else l = mid + 1;
        }
        return mountainArr.get(r) == target ? r : -1;
    }

    interface MountainArray {
        public int get(int index) {}
        public int length() {}
    }
}
