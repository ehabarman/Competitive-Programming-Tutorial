package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */
public class CoinChange {

    /**
     *
     * Problem Suammary:
     * Given a value N, if we want to make change for N cents,
     * and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
     * how many ways can we make the change? The order of coins doesn’t matter.
     *
     * For example, for N = 4 and S = {1,2,3},
     * there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
     * So output should be 4. For N = 10 and S = {2, 5, 3, 6},
     * there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
     * So the output should be 5.
     *
     */

    /**
     * this method solve the coin change problem dynamicly
     * @param S array contains coins values
     * @param n total sum of money
     * @return  all possible changes
     */
    public static int countChanges( int S[], int n )
    {
        // table[i] will be storing the number of solutions for
        // value i. We need n+1 rows as the table is constructed
        // in bottom up manner using the base case (n = 0)
        int table[]=new int[n+1];

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[] values
        // after the index greater than or equal to the value of the
        // picked coin
        for(int i=0; i<S.length; i++)
            for(int j=S[i]; j<=n; j++)
                table[j] += table[j-S[i]];

        return table[n];
    }

}
