package LC2700_3000;

public class LC2728_CountHousesinaCircularStreet {
    /**
     * You are given an object street of class Street that represents a circular street and a positive integer k which
     * represents a maximum bound for the number of houses in that street (in other words, the number of houses is less
     * than or equal to k). Houses' doors could be open or closed initially.
     *
     * Initially, you are standing in front of a door to a house on this street. Your task is to count the number of
     * houses in the street.
     *
     * The class Street contains the following functions which may help you:
     *
     * void openDoor(): Open the door of the house you are in front of.
     * void closeDoor(): Close the door of the house you are in front of.
     * boolean isDoorOpen(): Returns true if the door of the current house is open and false otherwise.
     * void moveRight(): Move to the right house.
     * void moveLeft(): Move to the left house.
     * Return ans which represents the number of houses on this street.
     *
     * Input: street = [0,0,0,0], k = 10
     * Output: 4
     *
     * Input: street = [1,0,1,1,0], k = 5
     * Output: 5
     *
     * Constraints:
     *
     * n == number of houses
     * 1 <= n <= k <= 10^3
     * @param street
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int houseCount(Street street, int k) {
        for (int i = 0; i < k; i++) {
            if (street.isDoorOpen()) street.closeDoor();
            street.moveRight();
        }
        int res = 1;
        street.openDoor();
        street.moveRight();
        while (!street.isDoorOpen()) {
            res++;
            street.moveRight();
        }
        return res;
    }

    class Street {
        public Street(int[] doors) {};
        public void openDoor(){};
        public void closeDoor(){};
        public boolean isDoorOpen(){return true;};
        public void moveRight(){};
        public void moveLeft(){};
    }
}