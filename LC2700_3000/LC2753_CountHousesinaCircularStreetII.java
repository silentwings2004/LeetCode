package LC2700_3000;

public class LC2753_CountHousesinaCircularStreetII {
    /**
     * You are given an object street of class Street that represents a circular street and a positive integer k which
     * represents a maximum bound for the number of houses in that street (in other words, the number of houses is less
     * than or equal to k). Houses' doors could be open or closed initially (at least one is open).
     *
     * Initially, you are standing in front of a door to a house on this street. Your task is to count the number of
     * houses in the street.
     *
     * The class Street contains the following functions which may help you:
     *
     * void closeDoor(): Close the door of the house you are in front of.
     * boolean isDoorOpen(): Returns true if the door of the current house is open and false otherwise.
     * void moveRight(): Move to the right house.
     * Note that by circular street, we mean if you number the houses from 1 to n, then the right house of housei is
     * housei+1 for i < n, and the right house of housen is house1.
     *
     * Return ans which represents the number of houses on this street.
     *
     * Input: street = [1,1,1,1], k = 10
     * Output: 4
     *
     * Input: street = [1,0,1,1,0], k = 5
     * Output: 5
     *
     * Constraints:
     *
     * n == number of houses
     * 1 <= n <= k <= 10^5
     * street is circular by definition provided in the statement.
     * The input is generated such that at least one of the doors is open.
     * @param street
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int houseCount(Street street, int k) {
        int res = 0;
        while (true) {
            for (int i = 0; i < k; i++) {
                if (street.isDoorOpen()) break;
                street.moveRight();
            }
            if (!street.isDoorOpen()) break;
            street.moveRight();
            int step = 0;
            for (int i = 0; i < k; i++) {
                step++;
                if (street.isDoorOpen()) break;
                street.moveRight();;
            }
            street.closeDoor();
            res = step;
        }
        return res;
    }

    class Street {
        public Street(int[] doors) {};
        public void closeDoor(){};
        public boolean isDoorOpen(){return true;};
        public void moveRight(){};
    }
}
