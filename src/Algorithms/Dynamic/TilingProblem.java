package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */
public class TilingProblem {

    /**
     * Problem summary:
     *
     * Given a “2 x n” board and tiles of size “2 x 1”,
     * count the number of ways to tile the given board using the 2 x 1 tiles.
     * A tile can either be placed horizontally i.e.,
     * as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
     *
     *
     * Input n = 3
     * Output: 3
     * Explanation:
     * We need 3 tiles to tile the board of size  2 x 3.
     * We can tile the board using following ways
     * 1) Place all 3 tiles vertically.
     * 2) Place first tile vertically and remaining 2 tiles horizontally.
     * 3) Place first 2 tiles horizontally and remaining tiles vertically
     *
     * Input n = 4
     * Output: 5
     * Explanation:
     * For a 2 x 4 board, there are 5 ways
     * 1) All 4 vertical
     * 2) All 4 horizontal
     * 3) First 2 vertical, remaining 2 horizontal
     * 4) First 2 horizontal, remaining 2 vertical
     * 5) Corner 2 vertical, middle 2 horizontal
     *
     */

    /**
     *  this problem has 2 solutions:
     *
     *  first one: this problem is application of fibonacci series, so the answer is:
     *  1, 1, 2, 3, 5, 8, 13 .... etc., with first term being answer for n = 1
     *
     *  second solution: this problem is the sum of all possible permutations.
     *  if you take horizantal tile as k and horizantal + vertical tiles as n
     *  then the answer = sum { permutations(n,k) } where k is [0,n-1]
     *
     *
     *
     *  note: second solution considered bad option for this problem when n value is large
     *  because of need of huge space when calculating permutations assuming using dynamic concept
     *  without dynamic concept the solution is quite slow
     */
}
