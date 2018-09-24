package Algorithms.Dynamic;


/**
 * @author  Ehab Arman
 * @Date    23-9-2018
 */
public class GameOptimalStrategy {

    /**
     *
     * Summary:
     * Problem statement: Consider a row of n coins of values v1 . . . vn,
     * where n is even. We play a game against an opponent by alternating turns.
     * In each turn, a player selects either the first or last coin from the row,
     * removes it from the row permanently, and receives the value of the coin.
     * Determine the maximum possible amount of money we can definitely win if we move first.
     *
     */

    /**
     *
     * Solution:
     *
     * F(i, j)  represents the maximum value the user can collect from
     *          i'th coin to j'th coin.
     *
     *     F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ),
     *                    Vj + min(F(i+1, j-1), F(i, j-2) ))
     * Base Cases
     *     F(i, j)  = Vi           If j == i
     *     F(i, j)  = max(Vi, Vj)  If j == i+1
     *
     *
     */

    // Utility functions to get maximum
    // and minimum of two intgers
    int max(int a, int b) { return a > b ? a : b; }
    int min(int a, int b) { return a < b ? a : b; }

    // Returns optimal value possible that a player
    // can collect from an array of coins of size n.
    // Note than n must be even
    static int optimalStrategyOfGame(int arr[], int n)
    {
        // Create a table to store solutions of subproblems
        int table[][] = new int[n][n];
        int gap, i, j, x, y, z;

        // Fill table using above recursive formula.
        // Note that the tableis filled in diagonal
        // fashion (similar to http:// goo.gl/PQqoS),
        // from diagonal elements to table[0][n-1]
        // which is the result.
        for (gap = 0; gap < n; ++gap) {
            for (i = 0, j = gap; j < n; ++i, ++j) {
                // Here x is value of F(i+2, j),
                // y is F(i+1, j-1) and z is
                // F(i, j-2) in above recursive formula
                x = ((i + 2) <= j) ? table[i + 2][j] : 0;
                y = ((i + 1) <= (j - 1)) ? table[i + 1][j - 1] : 0;
                z = (i <= (j - 2)) ? table[i][j - 2] : 0;

                table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
            }
        }

        return table[0][n - 1];
    }

    // Driver program
    public static void main(String[] args)
    {
        int arr1[] = { 8, 15, 3, 7 };
        int n = arr1.length;
        System.out.println("" + optimalStrategyOfGame(arr1, n));

        int arr2[] = { 2, 2, 2, 2 };
        n = arr2.length;
        System.out.println("" + optimalStrategyOfGame(arr2, n));

        int arr3[] = { 20, 30, 2, 2, 2, 10 };
        n = arr3.length;
        System.out.println("" + optimalStrategyOfGame(arr3, n));
    }


}
