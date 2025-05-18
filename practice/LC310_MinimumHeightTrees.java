package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MinimumHeightTrees
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 310. Minimum Height Trees
 */
public class LC310_MinimumHeightTrees {
    /**
     * For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then
     * a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
     * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
     * <p>
     * Format
     * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
     * undirected edges (each edge is a pair of labels).
     * <p>
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the
     * same as [1, 0] and thus will not appear together in edges.
     * <p>
     * Example 1 :
     * <p>
     * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     * <p>
     * 0
     * |
     * 1
     * / \
     * 2   3
     * <p>
     * Output: [1]
     * <p>
     * Example 2 :
     * <p>
     * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     * <p>
     * 0  1  2
     * \ | /
     * 3
     * |
     * 4
     * |
     * 5
     * <p>
     * Output: [3, 4]
     * <p>
     * 1、找出每个节点的度数
     * 定义degree[n]的数组，用来存储每个节点的度数，n为节点个数
     * degree[0]=1，degree[1]=1,degree[2]=1,degree[3]=4,degree[4]=2,degree[5]=1
     * <p>
     * 2、找出每个节点连接的节点
     * 0:{3},1:{3}，2:{3}, 3:{0,1,2}，4:{3,5} ，5:{4}
     * <p>
     * 3、度数为1的节点入队列
     * (0,1,2,5)
     * <p>
     * 4、循环砍掉加粗部分，砍到不能再砍
     * degree[0]=1，degree[1]=1,degree[2]=1,degree[3]=1,degree[4]=1,degree[5]=1
     * 结果是，队列为空之后，剩下的节点就是期望的结果，以该结果为根节点得到的树就是最小高度树
     * <p>
     * Note:
     * <p>
     * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
     * connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
     * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
     *
     * @param n
     * @param edges
     * @return
     */
    // BFS
    // time = O(n), space = O(n)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

    }
}