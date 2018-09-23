package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @date 23-9-2018
 */
public class TilingDominoes {

    /**
     *
     * Summary:
     *
     * Given a 3 x n board, find the number of ways to fill it with 2 x 1 dominoes.
     *
     * Examples :
     *
     * Input : 2
     * Output : 3
     *
     * Input : 8
     * Output : 153
     *
     * Input : 12
     * Output : 2131
     *
     */

    /**
     * Solution:
     *
     *  An = An-2 + 2* Bn-1
     *  Bn = An-1 + Bn-2
     *
     *  note: -1,-2 is subtracted from n to get previous term
     *
     *  A0 = 1, A1 = 0
     *  B0 = 0, B1 = 1
     *
     * An =  No. of ways to completely fill a 3 x n board. (We need to find this)
     * Bn =  No. of ways to fill a 3 x n board with top corner in last column not filled.
     *
     */

    public static int countWays(int n)
    {
        int []A = new int[n+1];
        int []B = new int[n+1];
        A[0] = 1; A[1] = 0;
        B[0] = 0; B[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            A[i] = A[i - 2] + 2 * B[i - 1];
            B[i] = A[i - 1] + B[i - 2];
        }

        return A[n];
    }

    // Driver code
    public static void main (String[] args)
    {
        int n = 8;
        System.out.println(countWays(n));
    }
}
