package LC3901_4200;

public class LC4080_DesignBusSeatBookingSystem {
    /**
     * You are asked to design a seat booking system for a bus.
     *
     * The bus has n rows. Each row has four seats labeled "A", "B", "C", and "D".
     *
     * Seats "A" and "D" are window seats; seats "B" and "C" are middle seats. In each row, seat "A" is adjacent to
     * seat "B", and seat "D" is adjacent to seat "C".
     *
     * Booking rules:
     *
     * Booking a middle seat ("B" or "C") always takes 1 second.
     * Booking a window seat ("A" or "D") takes 1 second if its adjacent middle seat is not booked, or 3 seconds (an
     * extra delay of 2 seconds) if its adjacent middle seat is already booked.
     * A seat is identified by a string such as "A1" or "D20" (one letter from A to D followed by its row number).
     *
     * Implement the BusBooking class:
     *
     * BusBooking(int n): Initializes the system with n empty rows.
     * void toggle(string seat): Books seat if it is free, or frees it if it is already booked.
     * int getTotalTime(): Returns the total time (in seconds) taken to book all currently booked seats, where the seats
     * are booked in the order their toggle calls were made. If no seats are currently booked, return 0.
     * int getMinTime(): Returns the smallest possible total time (in seconds) to book all currently booked seats, over
     * every possible order of booking them. If no seats are currently booked, return 0.
     *
     * Note: The time cost of a seat is fixed at the moment it is booked, based on the seats booked at that time.
     * Booking or freeing other seats afterward does not change the cost of a seat that is already booked.
     *
     * Input:
     * ["BusBooking", "toggle", "toggle", "getTotalTime", "getMinTime", "toggle", "toggle", "getTotalTime", "getMinTime"]
     * [[20], ["B3"], ["A3"], [], [], ["C3"], ["D3"], [], []]
     * Output:
     * [null, null, null, 4, 2, null, null, 8, 4]
     *
     * Input:
     * ["BusBooking", "toggle", "toggle", "getTotalTime", "toggle", "toggle", "toggle", "getTotalTime", "getMinTime"]
     * [[10], ["C7"], ["D7"], [], ["D7"], ["C7"], ["D7"], [], []]
     * Output:
     * [null, null, null, 4, null, null, null, 1, 1]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * seat[0] is one of 'A', 'B', 'C', 'D'.
     * The remaining characters of seat form an integer row with 1 <= row <= n.
     * The total number of calls to toggle, getTotalTime, and getMinTime does not exceed 105.
     * @param n
     */
    // time = O(1), space = O(n)
    boolean[][] st;
    int[][] cost;
    int tot, cnt;
    public LC4080_DesignBusSeatBookingSystem(int n) {
        st = new boolean[n + 1][4];
        cost = new int[n + 1][4];
        tot = 0;
        cnt = 0;
    }

    public void toggle(String seat) {
        int c = seat.charAt(0) - 'A';
        int r = Integer.parseInt(seat.substring(1));
        if (st[r][c]) {
            st[r][c] = false;
            tot -= cost[r][c];
            cost[r][c] = 0;
            cnt--;
        } else {
            int t = 1;
            if (c == 0 && st[r][1] || c == 3 && st[r][2]) t = 3;
            st[r][c] = true;
            cost[r][c] = t;
            tot += t;
            cnt++;
        }
    }

    public int getTotalTime() {
        return tot;
    }

    public int getMinTime() {
        return cnt;
    }
}
