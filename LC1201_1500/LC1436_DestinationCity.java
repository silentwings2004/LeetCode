package LC1201_1500;
import java.util.*;
public class LC1436_DestinationCity {
    /**
     * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from
     * cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
     *
     * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one
     * destination city.
     *
     * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
     * Output: "Sao Paulo"
     *
     * Input: paths = [["B","C"],["D","B"],["C","A"]]
     * Output: "A"
     *
     * Input: paths = [["A","Z"]]
     * Output: "Z"
     *
     * Constraints:
     *
     * 1 <= paths.length <= 100
     * paths[i].length == 2
     * 1 <= cityAi.length, cityBi.length <= 10
     * cityAi != cityBi
     * All strings consist of lowercase and uppercase English letters and the space character.
     * @param paths
     * @return
     */
    // time = O(n), space = O(n)
    public String destCity(List<List<String>> paths) {
        HashSet<String> set = new HashSet<>();
        for (List<String> p : paths) set.add(p.get(0));
        String res = "";
        for (List<String> p : paths) {
            if (!set.contains(p.get(1))) {
                res = p.get(1);
                break;
            }
        }
        return res;
    }
}