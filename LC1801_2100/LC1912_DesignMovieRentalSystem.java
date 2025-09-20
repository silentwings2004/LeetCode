package LC1801_2100;
import java.util.*;
public class LC1912_DesignMovieRentalSystem {
    /**
     * You have a movie renting company consisting of n shops. You want to implement a renting system that supports
     * searching for, booking, and returning movies. The system should also support generating a report of the currently
     * rented movies.
     *
     * Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there
     * is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a
     * movie moviei.
     *
     * The system should support the following functions:
     *
     * Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by
     * price in ascending order, and in case of a tie, the one with the smaller shopi should appear first. If there are
     * less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty
     * list should be returned.
     * Rent: Rents an unrented copy of a given movie from a given shop.
     * Drop: Drops off a previously rented copy of a given movie at a given shop.
     * Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res where
     * res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the shop shopj.
     * The movies in res should be sorted by price in ascending order, and in case of a tie, the one with the smaller
     * shopj should appear first, and if there is still tie, the one with the smaller moviej should appear first. If
     * there are fewer than 5 rented movies, then all of them should be returned. If no movies are currently being rented,
     * then an empty list should be returned.
     * Implement the MovieRentingSystem class:
     *
     * MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and the
     * movies in entries.
     * List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie as
     * described above.
     * void rent(int shop, int movie) Rents the given movie from the given shop.
     * void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
     * List<List<Integer>> report() Returns a list of cheapest rented movies as described above.
     * Note: The test cases will be generated such that rent will only be called if the shop has an unrented copy of the
     * movie, and drop will only be called if the shop had previously rented out the movie.
     *
     * Input
     * ["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
     * [[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
     * Output
     * [null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]
     *
     * Constraints:
     *
     * 1 <= n <= 3 * 10^5
     * 1 <= entries.length <= 10^5
     * 0 <= shopi < n
     * 1 <= moviei, pricei <= 10^4
     * Each shop carries at most one copy of a movie moviei.
     * At most 10^5 calls in total will be made to search, rent, drop and report.
     * @param n
     * @param entries
     */
    // time = O(mlogm), space = O(m)
    HashMap<Integer, TreeSet<int[]>> map;
    HashMap<String, Integer> hm;
    TreeSet<int[]> rent;
    public LC1912_DesignMovieRentalSystem(int n, int[][] entries) {
        map = new HashMap<>();
        hm = new HashMap<>();
        rent = new TreeSet<>((o1, o2) -> o1[2] != o2[2] ? o1[2] - o2[2] : (o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]));
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            map.putIfAbsent(movie, new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]));
            map.get(movie).add(new int[]{price, shop});
            hm.put(get(shop, movie), price);
        }
    }

    public List<Integer> search(int movie) {
        if (!map.containsKey(movie)) return new ArrayList<>();
        TreeSet<int[]> set = map.get(movie);
        List<Integer> res = new ArrayList<>();
        for (int[] x : set) {
            res.add(x[1]);
            if (res.size() == 5) break;
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = hm.get(get(shop, movie));
        map.get(movie).remove(new int[]{price, shop});
        rent.add(new int[]{shop, movie, price});
    }

    public void drop(int shop, int movie) {
        int price = hm.get(get(shop, movie));
        map.get(movie).add(new int[]{price, shop});
        rent.remove(new int[]{shop, movie, price});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] x : rent) {
            res.add(Arrays.asList(x[0], x[1]));
            if (res.size() == 5) break;
        }
        return res;
    }

    private String get(int a, int b) {
        return a + "#" + b;
    }
}