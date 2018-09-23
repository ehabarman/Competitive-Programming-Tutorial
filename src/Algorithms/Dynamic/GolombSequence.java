package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */
public class GolombSequence {


    /**
     *
     * Summary:
     *
     * In mathematics, the Golomb sequence is a non-decreasing integer sequence where n-th term is equal to number of times n appears in the sequence.
     *
     * The first few values are
     * 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, ……
     *
     * Explanation of few terms:
     * Third term is 2, note that three appears 2 times.
     * Second term is 2, note that two appears 2 times.
     * Fourth term is 3, note that four appears 3 times.
     *
     */

    /**
     * Given a positive integer n. The method returns first n terms of Golomb Sequence
     * @param n last term to be calculated
     */

    public static void printGolomb(int n)
    {
        int dp[] = new int[n + 1];

        // base cases
        dp[1] = 1;
        System.out.print(dp[1] + " ");

        // Finding and printing first n
        // terms of Golomb Sequence.
        for (int i = 2; i <= n; i++)
        {
            dp[i] = 1 + dp[i - dp[dp[i - 1]]];

            System.out.print(dp[i] + " ");
        }
    }

    // Driver code
    public static void main (String[] args)
    {
        int n = 9;

        printGolomb(n);
    }
}
